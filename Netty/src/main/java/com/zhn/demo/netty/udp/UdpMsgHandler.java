package com.zhn.demo.netty.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class UdpMsgHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String request = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println("接收发送端的消息：" + request);
        System.out.println("发送端的ip地址：" + ctx.channel().remoteAddress());
        System.out.println("回答发送端已经收到消息。。。");

        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("ni hao ...", CharsetUtil.UTF_8),
                new InetSocketAddress("192.168.0.121", 9999)));
    }
}
