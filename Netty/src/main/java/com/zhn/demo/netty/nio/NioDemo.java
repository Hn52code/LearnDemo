package com.zhn.demo.netty.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioDemo {
    public static void main(String[] args) throws IOException {
        // 第一步打开服务通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 第二步绑定端口号
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
        serverSocketChannel.bind(address);
        // 第三步创建Reactor线程 创建多路复用器，启动
        Selector selector = Selector.open();

        // 第四步将serversocketchannel注册到Reactor线程selector 监听accept事件
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int num = selector.select();
        Set selectKeys = selector.selectedKeys();
        Iterator it = selectKeys.iterator();
        while (it.hasNext()) {
            SelectionKey key1 = (SelectionKey) it.next();
        }

    }
}
