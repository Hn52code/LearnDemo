package com.zhn.demo.netty.netty3;

import com.zhn.demo.netty.netty3.msg.Message;
import com.zhn.demo.netty.netty3.receive.ProtocolCmd;
import com.zhn.demo.netty.netty3.receive.ProtocolCons;
import com.zhn.demo.netty.netty3.receive.ProtocolStatus;
import com.zhn.demo.netty.netty3.receive.ReceivingDatagram;
import com.zhn.demo.netty.netty3.reply.ProtocolAnalysisErrorType;
import com.zhn.demo.netty.netty3.reply.RepliedPacketFactory;
import com.zhn.demo.netty.netty3.server.MessagePushService;
import com.zhn.demo.netty.netty3.server.WrapTransferDataHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IotDecodeWrapDataHandler extends ChannelInboundHandlerAdapter {
    // log日志
    private static final Logger log = LogManager.getLogger(IotDecodeWrapDataHandler.class);
    // 连接对象闲置唤醒的记录值
    private int awakeIdle = 0;

    // 通道被注册时调用方法
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        DeviceConnectManager.getConnectCount().addAndGet(1);
        log.info("[服务端信息-统计]-->>总连接数：" + DeviceConnectManager.getConnectCount());
    }

    // 通道被注销时调用的方法
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        // 注销时，统计数量减少
        DeviceConnectManager.getConnectCount().decrementAndGet();
        log.info("[服务端信息-统计-切断]-->>总连接数：" + DeviceConnectManager.getConnectCount());
        if (DeviceConnectManager.getConfirmRuleCount().remove(ctx)) {
            log.warn("[服务端信息-统计-切断]-->>连接数--符合规则：" + DeviceConnectManager.getConfirmRuleCount().size());
        }

        String mac = DeviceConnectManager.removeDefinedByValueReturnKey(ctx.channel());
        if (mac != null) {
            Message message = new Message();
            message.setCmd("outline");
            message.setMac(mac);
            SpringContextUtil.getBean(MessagePushService.class).outline(message);
            log.info("[服务端信息-统计]-->>连接数--已定义：" + DeviceConnectManager.getConfirmRuleDefinedCount().size());
        }
        String str = DeviceConnectManager.getConfirmRuleDefinedCount2().remove(ctx.channel());
        if (str != null) {
            log.warn("[服务端信息-统计-切断]设备ID：" + str + "-->>连接数--已读取发消息："
                    + DeviceConnectManager.getConfirmRuleDefinedCount2().size());
        }
        str = DeviceConnectManager.getConfirmRuleDefinedCount3().remove(ctx.channel());
        if (str != null) {
            log.warn("[服务端信息-统计-切断]设备ID：" + str + "-->>连接数--心跳："
                    + DeviceConnectManager.getConfirmRuleDefinedCount3().size());
        }
        str = DeviceConnectManager.getConfirmRuleDefinedCount4().remove(ctx.channel());
        if (str != null) {
            log.warn("[服务端信息-统计-切断]设备ID：" + str + "-->>连接数--登录："
                    + DeviceConnectManager.getConfirmRuleDefinedCount4().size());
        }
        str = DeviceConnectManager.getConfirmRuleDefinedCount5().remove(ctx.channel());
        if (str != null) {
            log.warn("[服务端信息-统计-切断]设备ID：" + str + "-->>连接数--实时数值："
                    + DeviceConnectManager.getConfirmRuleDefinedCount5().size());
        }
        // 传递给下一个handler
        // ctx.fireChannelUnregistered();
    }

    // 当监听通道有字节流动，则调用该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(ctx.name());
//        System.out.println(ctx.toString());
//        System.out.println(ctx.channel());
//        System.out.println(ctx.channel().id());
//        System.out.println(ctx.channel().toString());
    }

    // 当监听通道处于空闲，则调用该方法
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("通道闲置");
    }

    // 解码正确时，被视为读取消息，调用该方法。IotDecodeWrapDataHandler 64 channelUnregistered - [服务端信息-统计-切断]设备ID
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 新增通道，并打印日志
        if (DeviceConnectManager.getConfirmRuleCount().add(ctx)) {
            log.warn("[服务端信息-统计]-->>连接数--符合规则：" + DeviceConnectManager.getConfirmRuleCount().size());
        }
        byte[] data = (byte[]) msg;
        ByteBuf buf = Unpooled.wrappedBuffer(data);
        ReceivingDatagram datagram = dataDecodeAndWrap(buf, ctx);
        if (datagram != null) {
            // 闲置唤醒参数复位
            awakeIdle = 0;
            // 打包和中转
            SpringContextUtil.getBean(WrapTransferDataHandler.class).wrap(ctx, datagram);
        } else {
            if (DeviceConnectManager.getConfirmRuleUndefinedCount().add(ctx.channel())) {
                log.info("[服务端信息-统计]-->>连接数--未定义：" + DeviceConnectManager.getConfirmRuleUndefinedCount().size());
            }
        }
        // release函数使得对象引用计数减1，释放后，
        // 再次调用该对象引用，将不再往下执行？若其他可执行此处疑问
        buf.release();
    }

    // 解码
    private ReceivingDatagram dataDecodeAndWrap(ByteBuf buf, ChannelHandlerContext ctx) {
        // 读取命令位
        int cmdKey = buf.getShort(ProtocolCons.MAC_OFFSET);
        ProtocolCmd cmd = ProtocolCmd.getDataCmd(cmdKey);
        // System.out.println("读取的命令值：" + cmdKey);
        if (cmd == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_CMD));
            return null;
        }
        // 读取MAC位
        byte[] macBytes = new byte[ProtocolCons.MAC_OFFSET];
        buf.readBytes(macBytes);
        String mac = ByteUtil.byte2Mac2(macBytes);
        // System.out.println("读取的mac地址：" + mac);
        buf.skipBytes(2);
        // 读取状态位
        int staByte = buf.readByte();
        ProtocolStatus sta = ProtocolStatus.getOrdinal(staByte);
        if (sta == null) {
            ctx.writeAndFlush(RepliedPacketFactory.
                    getErrorReply(ProtocolAnalysisErrorType.ERROR_STA));
            return null;
        }
        // System.out.println("读取的状态位：" + statusByte);
        // System.out.println("转换的状态位：" + sta);
        // 读取信息位
        ByteBuf data = buf.readBytes(buf.readableBytes());
        // System.out.println("读取的信息位(hex)：" + ByteBufUtil.hexDump(data));
        ReceivingDatagram datagram = new ReceivingDatagram();
        datagram.setMac(mac);
        datagram.setCmd(cmd);
        datagram.setStatus(sta);
        datagram.setData(data);
        return datagram;
    }

    // 用户注册事件触发方法
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        /* 判断事件是否为闲置事件*/
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            // 处理读取事件闲置
            if (event.state() == IdleState.READER_IDLE) {
                // 第三次闲置后切断连接
                if (awakeIdle > 2) {
                    ctx.close();
                    return;
                }
                ctx.writeAndFlush(RepliedPacketFactory.getPingReply());
                awakeIdle++;
            }
        }
    }

    // 异常时触发的方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage());
    }


}
