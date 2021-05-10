package com.zhn.demo.netty.netty5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class ServerManager {

    /* 服务配置*/
    private ServerConfig serverConfig;
    /* 服务连接线程池 */
    private NioEventLoopGroup boss;
    /* 服务工作线程池 */
    private NioEventLoopGroup work;
    /* 服务启动串联器 */
    private ServerBootstrap bootstrap;
    /* 服务配置是否初始化 */
    private boolean isInitBootstrap = false;
    /* 服务通道监听 */
    private ChannelFuture channelFuture;

    /* 启动服务 */
    public void startServer() {
        /* 创建线程池 */
        initNioEventLoopGroup();
        /* 创建服务连接器 */
        initServerBootstrap();
        /* 绑定端口，开启服务，*/
        bindServer();
    }

    public void shutdownServer() {
        if (channelFuture != null) channelFuture = null;
        if (boss != null) boss.shutdownGracefully();
        if (work != null) work.shutdownGracefully();
    }

    private void initNioEventLoopGroup() {
        if (boss == null) boss = new NioEventLoopGroup();
        if (work == null) work = new NioEventLoopGroup();
    }

    private void initServerBootstrap() {
        if (isInitBootstrap) return;
        bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IdleStateHandler(5,0,0));
                        pipeline.addLast(new LoggingHandler());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ServerMsgHandler());
                    }
                });
        isInitBootstrap = true;
    }

    private void bindServer() {
        try {
            // 绑定端口，异步开启服务
            channelFuture = bootstrap.bind(serverConfig.getPort()).sync();
            // 等待服务端监听端口关闭，此处 阻塞 主线程后续代码执行。
            // channelFuture.channel().closeFuture().sync();
            // 可改为 给 连接通道 添加一个未来通道关闭事件
            channelFuture.channel().closeFuture().addListener(future -> {
                boss.shutdownGracefully();
                work.shutdownGracefully();
                System.out.println("close server");
            });
        } catch (Exception e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public ServerBootstrap getBootstrap() {
        return bootstrap;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public boolean isInitBootstrap() {
        return isInitBootstrap;
    }

    public void setInitBootstrap(boolean initBootstrap) {
        isInitBootstrap = initBootstrap;
    }
}
