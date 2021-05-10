package com.zhn.demo.netty.netty2.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyTCPServer {
    // netty启动日志
    private final static Logger logger = LogManager.getLogger(NettyTCPServer.class);

    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NettyTCPServer() {
        super();
    }

    public NettyTCPServer(int port) {
        this.port = port;
    }

    // 启动NettyTcpServer
    public void start() {
        // 负责监听客户端连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 负责处理客户端数据传输
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyTCPServerChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.ALLOCATOR,  PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            logger.info("[服务端消息]：Netty服务启动--->开放端口 " + this.port);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("[服务端消息]：Netty服务启动时错误");
            e.printStackTrace();
        } finally {
            logger.info("[服务端消息]：Netty服务优雅关闭");
            // 优雅关闭
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyTCPServer(8001).start();
    }
}
