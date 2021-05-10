package com.zhn.demo.netty.netty6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
//                        pipeline.addLast(new IdleStateHandler(5, 0, 0));
                        pipeline.addLast(new LoggingHandler());
                        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[]{(byte) 0xa8})));
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(1023, 0, 2, 0, 2));
                        pipeline.addLast("decode", new MyDecoder());
                    }
                });
        ChannelFuture future = bootstrap.bind(8888).sync();
//        future.channel().closeFuture().sync();


//        boss.shutdownGracefully().sync();
//        work.shutdownGracefully().sync();


//        ExecutorService service =  Executors.newCachedThreadPool();

//        service.shutdown();
    }




}
