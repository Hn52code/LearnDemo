package com.zhn.demo.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class UdpNettyClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpClientMsgHandler());
            Channel channel = bootstrap.bind(9988).sync().channel();
            channel.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("hello!!!", CharsetUtil.UTF_8),
                    new InetSocketAddress("127.0.0.1",8888))).sync();
            channel.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
