package com.zhn.demo.netty.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientMsgHandler extends SimpleChannelInboundHandler<Byte[]> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭连接，删除里连接信息
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Byte[] msg) throws Exception {

    }




}
