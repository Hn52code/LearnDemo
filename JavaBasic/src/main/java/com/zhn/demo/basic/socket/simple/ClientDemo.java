package com.zhn.demo.basic.socket.simple;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientDemo {

    public static void main(String[] args) throws IOException {

        int port = 8888;
        InetAddress address = InetAddress.getLocalHost();
        Socket socket = new Socket(address, port);
        System.out.println("Connected Server ...");

        BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        // 接收服务端消息
        byte[] content = new byte[256];
        while ((bin.read(content)) != -1) {
            System.out.println(new String(content));
            // 回复服务端
            writer.write("Hello Server ...");
            writer.flush();
        }
        bin.close();
        socket.close();
    }

}
