package com.zhn.demo.netty.netty5;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientConnector {

    /* 服务器连接配置 */
    private ConnectConfig connectConfig;
    /* 连接线程池*/
    private NioEventLoopGroup work;
    /* 连接启动器 */
    private Bootstrap bootstrap;
    /* 连接成功后的通道 */
    private ChannelFuture channelFuture;
    /* 通道 */
    private Channel channel;

    /* 连接服务端 */
    public void connectServer() {
        // 创建工作线程池
        initEventLoopGroup();
        // 初始化启动项配置
        initBootstrap();
        // 连接指定地址通道
        connectChannel();
    }

    private void initEventLoopGroup() {
        work = new NioEventLoopGroup();
    }

    private void initBootstrap() {
        bootstrap = new Bootstrap();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ClientMsgHandler());
                    }
                });
    }

    private void connectChannel() {
        try {
            channelFuture = bootstrap.connect(connectConfig.getIp(), connectConfig.getPort()).sync();
            if (channelFuture.isSuccess()) {
                channelFuture.channel().closeFuture().addListener(future -> {
                    work.shutdownGracefully();
                    System.out.println("client close ...");
                });
                channel = channelFuture.channel();
            }
        } catch (Exception e) {
            e.printStackTrace();
            work.shutdownGracefully();
        }
    }

    public void shutdownConnection() {
        if (work != null) work.shutdownGracefully();
    }

    public void setConnectConfig(ConnectConfig connectConfig) {
        this.connectConfig = connectConfig;
    }

    public ConnectConfig getConnectConfig() {
        return connectConfig;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public Channel getChannel() {
        return channel;
    }
}
