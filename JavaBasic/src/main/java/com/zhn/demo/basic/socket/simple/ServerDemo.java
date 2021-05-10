package com.zhn.demo.basic.socket.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

    public static void main(String[] args) throws IOException {
        int port = 8888;
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        System.out.println("A New Client Connected ...");
        // 向客户端发送消息
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.write("welcome a client");
        writer.write("\n");
        writer.flush();
        // 接收客户端的消息
        BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
        byte[] content = new byte[256];
        while ((bin.read(content)) != -1) {
            System.out.println(new String(content));
        }
        bin.close();

        writer.close();
        socket.close();
        server.close();
    }

}
