package com.zhn.demo.netty.netty2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private int temp = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] b = (byte[]) msg;
//        System.out.println(ByteBufUtil.hexDump(b));
        if (temp != 0) {
            // a8 00 14 313131 313131 ffffffffffffffffffff 00 01 ff 1c
            byte[] arr = {(byte) 0xa8, 0x00, 0x14, 0x31, 0x31, 0x31, 0x31, 0x31, 0x31,
                    (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                    (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                    0x00, 0x01, (byte) 0xff, (byte) 0x1c,};
            ctx.writeAndFlush(arr);
            temp--;
        }
        temp++;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent idle = (IdleStateEvent) evt;
//            if (idle.state() == IdleState.READER_IDLE) {
//
//            }
//        }
    }
}
