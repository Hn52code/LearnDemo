package com.zhn.demo.netty.netty5;

public class Client {

    public static void main(String[] args) {
        ConnectConfig config = new ConnectConfig();
        config.setIp("localhost");
        config.setPort(8888);
        ClientConnector connector = new ClientConnector();
        connector.setConnectConfig(config);
        connector.connectServer();
        connector.getChannel().writeAndFlush("ASafaswerq");
    }
}
