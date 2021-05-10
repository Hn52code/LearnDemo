package com.zhn.demo.socketio.simple;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class HelloWorld {

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9999);
        SocketIOServer server = new SocketIOServer(config);
        // 连接事件
        server.addConnectListener(client -> {

            System.out.println(server.getAllClients());
            System.out.println(client.getSessionId());
            client.sendEvent("client-rec", "you are welcome!");
        });

        server.addEventListener("server-rec", String.class, (client, data, ackRequest) -> {
            System.out.println(client.getSessionId());
            System.out.println(data);
            System.out.println(ackRequest);
        });
        // 断开连接事件
        server.addDisconnectListener(client -> {
            System.out.println(client.getSessionId() + " 断开连接");
        });
        server.start();
    }
}
