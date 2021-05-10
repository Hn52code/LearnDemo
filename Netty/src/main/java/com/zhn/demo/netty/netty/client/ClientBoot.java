package com.zhn.demo.netty.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientBoot {

    public void start() {
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new ClientMsgHandler());
                        }
                    });
            // 绑定地址端口连接
            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
            if (future.isSuccess()) { //判断连接与否
                Channel channel = future.channel();
                // A8 00 15 313131 313131 FFFFFF FFFFFF FFFFFF FF 00 02 FF 11 2E
                byte[] arr = {(byte) 0xa8, 0x00, 0x15, 0x31, 0x31, 0x31, 0x31, 0x31, 0x31,
                        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                        0x00, 0x02, (byte) 0xff, 0x11, (byte) 0xee,};
                channel.writeAndFlush(arr);
            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 300; i++) {
            System.out.println("启动客户端剩余数量" + i);
            Thread thread = new Thread(() -> {
                new ClientBoot().start();
            });
            thread.start();
        }
        Thread.sleep(5000);
        for (int i = 300; i < 600; i++) {
            System.out.println("启动客户端剩余数量" + i);
            Thread thread = new Thread(() -> {
                new ClientBoot().start();
            });
            thread.start();
        }
        Thread.sleep(5000);
        for (int i = 600; i < 900; i++) {
            System.out.println("启动客户端剩余数量" + i);
            Thread thread = new Thread(() -> {
                new ClientBoot().start();
            });
            thread.start();
        }
    }

}
