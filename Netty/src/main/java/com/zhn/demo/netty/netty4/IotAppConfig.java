package com.zhn.demo.netty.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class IotAppConfig {

    @Value("${net.port}")
    private int port;

    private NioEventLoopGroup boss;
    private NioEventLoopGroup work;
    private IoTServerChannelInitializer ioTServerChannelInitializer;

    @Autowired
    @Qualifier("boss")
    public void setBoss(NioEventLoopGroup boss) {
        this.boss = boss;
    }

    @Autowired
    @Qualifier("work")
    public void setWork(NioEventLoopGroup work) {
        this.work = work;
    }

    @Autowired
    public void setIoTServerChannelInitializer(IoTServerChannelInitializer ioTServerChannelInitializer) {
        this.ioTServerChannelInitializer = ioTServerChannelInitializer;
    }

    @Bean(name = "boss", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup createBossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(name = "work", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup createWorkGroup() {
        return new NioEventLoopGroup();
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("shutdown");
        if (boss != null) boss.shutdownGracefully();
        if (work != null) work.shutdownGracefully();
    }


    @PostConstruct
    public void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(ioTServerChannelInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            ;
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("关闭。。。");
                        boss.shutdownGracefully();
                        work.shutdownGracefully();
                    }

                }
            });
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
