package com.zhn.demo.basic.socket.general;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerBoot {

    public static void main(String[] args) throws IOException {
        int count = 0;
        // 端口号
        int port = 8888;
        // 创建服务端socket
        ServerSocket serverSocket = new ServerSocket(port);
        // 监听客户端连接
        while (true) {
            
        }

    }
}
