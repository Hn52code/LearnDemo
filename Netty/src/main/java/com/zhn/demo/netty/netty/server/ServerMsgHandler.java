package com.zhn.demo.netty.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerMsgHandler extends SimpleChannelInboundHandler<String> {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = onlineCount.addAndGet(1);
        System.out.println("服务端在线数量：" + i);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        int i = onlineCount.decrementAndGet();
        // 关闭连接，删除里连接信息
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭连接，删除里连接信息
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端接收消息：" + ctx.channel().id() + msg);
        ctx.writeAndFlush("comeback message");
    }

    // 闲置连接处理
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
//                ctx.writeAndFlush("服务端接收消息闲置中。。。");
            }
//            if (event.state() == IdleState.WRITER_IDLE) {
//                ctx.writeAndFlush("服务端发送消息闲置中。。。");
//            }
        }
    }

}
