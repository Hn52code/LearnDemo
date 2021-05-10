package com.zhn.demo.netty.netty7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup work;
        Class clz;
        if (Epoll.isAvailable()) {
            work = new EpollEventLoopGroup();
            clz = EpollSocketChannel.class;
        } else {
            work = new NioEventLoopGroup();
            clz = NioSocketChannel.class;
        }
        bootstrap.group(work)
                .channel(clz);


    }

}
