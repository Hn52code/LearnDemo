package com.zhn.demo.netty.netty2.server;

import com.zhn.demo.netty.netty2.server.msg.emun.CmdType;
import com.zhn.demo.netty.netty2.server.msg.emun.StaType;
import com.zhn.demo.netty.netty2.server.msg.recei.*;
import com.zhn.demo.netty.netty2.server.msg.send.SendReqMsgPing;
import com.zhn.demo.netty.netty2.server.msg.send.SendRespMsgAlarm;
import com.zhn.demo.netty.netty2.server.msg.send.SendRespMsgFault;
import com.zhn.demo.netty.netty2.server.msg.send.SendRespMsgLogin;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NettyTCPServerMsgHandler extends SimpleChannelInboundHandler<ReceiveMsg> {

    private final static Logger logger = LogManager.getLogger(NettyTCPServerMsgHandler.class);

    private final static ConcurrentHashMap<Channel, String> map = new ConcurrentHashMap<>();

    private static AtomicInteger onlineCount = new AtomicInteger(0);
    // 当读取响应方法接收到正确的数据包时，该变量归零
    private int try_connect_count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = onlineCount.addAndGet(1);

        if (map.containsKey(ctx.channel())) {
            System.out.println("活跃的连接ID：" + ctx.channel().id() + "_设备ID：" + map.get(ctx.channel()));
        } else {
            System.out.println("活跃的连接ID：" + ctx.channel().id());
            map.put(ctx.channel(), "");
        }
        onlineClients(i);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        int i = onlineCount.decrementAndGet();
        onlineClients(i);
        // 关闭连接，删除里连接信息
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭连接，删除里连接信息
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReceiveMsg msg) throws Exception {

        map.put(ctx.channel(), msg.getSendID());
        short comValue = msg.getMsgCom();
        // 获取命令类型
        CmdType cmdType = CmdType.getCmdType(comValue);
        try_connect_count = 0;
        if (cmdType == CmdType.PING) {
            logger.debug("[设备端发送消息]：心跳指令,DeviceID：" + msg.getSendID());
            // 心跳处理，此处取消回消息给客户端，在检验客户端时，再次主动回复心跳
            // ctx.writeAndFlush(new SendMsgPing());
        } else {
            handlerMsg(cmdType, ctx, msg);
        }
    }

    // 闲置连接处理
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            // 服务端对应着读事件，当为READER_IDLE时触发
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                try_connect_count++;
                logger.debug("[服务端提示消息]：" + ctx.channel().id() + "通道读取空闲中，已发送心跳提示");
                if (try_connect_count < 4) {
                    // 发送心跳
                    ctx.channel().writeAndFlush(new SendReqMsgPing());
                } else {
                    logger.debug("[服务端提示消息]：自动关闭" + ctx.channel().id() + "设备端通道");
                    // 通知连接断开推送消息
                    ctx.close();
                }
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                // 服务端对应着写事件，当为WRITER_IDLE时触发
                logger.debug("[服务端提示消息]：" + ctx.channel().id() + "通道写入闲置中");
            }
        }
    }

    private void handlerMsg(CmdType type, ChannelHandlerContext ctx, ReceiveMsg msg) {
        switch (type) {
            case LOGIN: // 登录
                ReceiveReqMsgLogin login = (ReceiveReqMsgLogin) msg;
                logger.debug("[设备端发送消息]：登录指令" + login.toString());
                ctx.writeAndFlush(new SendRespMsgLogin(StaType.SUCCESS));

                break;
            case ALARM: // 报警
                ReceiveReqMsgAlarm alarm = (ReceiveReqMsgAlarm) msg;
                ctx.writeAndFlush(new SendRespMsgAlarm(StaType.SUCCESS));
                logger.debug("[设备端发送消息]：报警指令" + alarm.toString());
                break;
            case FAULT: // 故障
                ReceiveReqMsgFault fault = (ReceiveReqMsgFault) msg;
                ctx.writeAndFlush(new SendRespMsgFault(StaType.SUCCESS));
                logger.debug("[设备端发送消息]：故障指令" + fault.toString());
                break;
            case RESET: // 重置
                ReceiveRespMsgReset reset = (ReceiveRespMsgReset) msg;
                logger.debug("[设备端回复消息]：复位指令" + reset.toString());
                break;
            case CTRL_SWITCH:
                ReceiveRespMsgCtrlSwitch ctrl = (ReceiveRespMsgCtrlSwitch) msg;
                logger.debug("[设备端回复消息]：开关指令" + ctrl.toString());
                break;
            case CTRL_PING:
                ReceiveRespMsgCtrlPing ping = (ReceiveRespMsgCtrlPing) msg;
                break;
            default:
                logger.debug("[设备端回复消息]：未知命令");
        }
    }

    private void onlineClients(int i) {
        logger.info("[服务端提示消息]：在线设备数量 " + i);
    }

}
