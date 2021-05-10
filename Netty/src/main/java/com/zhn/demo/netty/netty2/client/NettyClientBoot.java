package com.zhn.demo.netty.netty2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NettyClientBoot {

    private final static Logger logger = LogManager.getLogger(NettyClientBoot.class);

    public void start(String ip, int port) {
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
                    p.addLast(new ClientDecoder());
                    p.addLast(new ClientEncoder());
                    p.addLast(new NettyClientHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            ChannelFuture future = bootstrap.connect(ip, port);
            if (future.isSuccess()) {
                Channel channel = future.channel();
                // A8 00 15 313131 313131   FF 00 02 FF 0a 27
                byte[] arr = {(byte) 0xa8, 0x00, 0x15, 0x31, 0x31, 0x31, 0x31, 0x31, 0x31,
                        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                        (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                        0x00, 0x02, (byte) 0xff, 0x0a, (byte) 0x27};
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
//        moreThread(0, 100);
        moreThread(0, 300);
        moreThread(300, 600);
        moreThread(600, 900);
//        moreThread(300, 600);
//        moreThread(300, 600);
    }

    public static void moreThread(int index, int len) throws Exception {
        if (index!=0){
            Thread.sleep(3000);
        }
        for (int i = index; i < len; i++) {
            logger.debug("启动客户端数量" + i);
            Thread.sleep(100);
            Thread thread = new Thread(() -> {
                new NettyClientBoot().start("47.92.205.113", 8001);
//                new NettyClientBoot().start("localhost", 8001);
//                new NettyClientBoot().start("192.168.150.130", 8001);
            });
            thread.start();
        }
    }

}
