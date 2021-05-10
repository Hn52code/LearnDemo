package com.zhn.demo.netty.netty7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        final EventLoopGroup boss;
        final EventLoopGroup work;
            if (Epoll.isAvailable()) {
                boss = new EpollEventLoopGroup();
                work = new EpollEventLoopGroup();
            } else {
                boss = new NioEventLoopGroup();
                work = new NioEventLoopGroup();
        }
        serverBootstrap.group(boss, work);
        serverBootstrap.channel(Epoll.isAvailable() ?
                EpollServerSocketChannel.class : NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new LoggingHandler());
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast(new MessageHandler());
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
        Channel parentChannel = channelFuture.channel();
        parentChannel.closeFuture().addListener(future -> {
            if (!future.isSuccess()) {
                System.out.println("closing");
                boss.shutdownGracefully();
                work.shutdownGracefully();
            }
        });
//        System.out.println("-----");
//        Thread.sleep(10000);
//        parentChannel.close();
//        parentChannel = null;
    }

}
