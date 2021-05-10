package com.zhn.demo.netty.netty3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class IotNettyServer {

    private final static Logger logger = LogManager.getLogger(IotNettyServer.class);

    public void start(int port) {
        // 负责连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 负责工作
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            // 启动器
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
                            pipeline.addLast(new IotProtocolDecoder());
                            pipeline.addLast(new IotProtocolEncoder());
                            pipeline.addLast(new IotDecodeWrapDataHandler());
                        }
                    });
            logger.info("[服务端消息]：Iot服务启动--->开放端口 " + port);
            ChannelFuture future = boot.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.info("[服务端消息]：Iot服务启动时错误");
            e.printStackTrace();
        } finally {
            logger.info("[服务端消息]：Iot服务优雅关闭");
            // 优雅关闭
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

}
