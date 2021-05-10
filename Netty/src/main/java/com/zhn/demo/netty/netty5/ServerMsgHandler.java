package com.zhn.demo.netty.netty5;

import com.zhn.demo.netty.netty3.ByteUtil;
import com.zhn.demo.netty.netty5.msg.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerMsgHandler extends SimpleChannelInboundHandler<byte[]> {

    private int idleCount = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] data) throws Exception {
        idleCount = 0;
        ByteBuf in = Unpooled.wrappedBuffer(data);
        Request request = resolve(in);
        process(in, request);
        in.release();
    }


    private Request resolve(ByteBuf in) {
        byte[] bytes = new byte[16];
        in.readBytes(bytes);
        String modelId = ByteUtil.byte2Mac2(bytes);
        int command = in.readShort();
        int sta = in.readUnsignedByte();

        ReqMsgHead head = new ReqMsgHead();
        head.setStatus(sta);
        head.setCommand(command);
        head.setModelId(modelId);
        head.setMsgType(sta == 0xff ? 0 : 1);
        Request request = new Request();
        request.setHead(head);
        return request;
    }

    private void process(ByteBuf in, Request request) {
        ReqMsgHead head = request.getHead();
        if (head.getMsgType() == 0) {
            ReqMsgBody body = null;
            switch (head.getCommand()) {
                case 2:
                    body = new ReqUpInitMsg();
                    break;
                case 3:
                    body = new ReqUptCheckMsg();
                    break;
                case 7:
                    body = new ReqUpResetMsg();
                    break;
                case 8:
                    body = new ReqUpStatusMsg();
                    break;
                case 0x0f:
                    body = new ReqUpDefaultMsg();
                    break;
            }
            request.setBody(body);
        } else {

        }
    }

    private void handle(ChannelHandlerContext ctx) {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent ev = (IdleStateEvent) evt;
            if (ev.state() == IdleState.READER_IDLE) {
                idleCount++;
                if (idleCount > 3) ctx.close();
                ctx.writeAndFlush("hehe");
            }
        }
//        else {
//            super.userEventTriggered(ctx, evt);
//        }
    }
}
