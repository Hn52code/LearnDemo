package com.zhn.demo.netty.netty5;

public class ServerBoot {
    public static void main(String[] args) {
        System.out.println("服务正在启动中。。。");
        ServerManager manager = new ServerManager();
        ServerConfig config = new ServerConfig();
        config.setPort(8888);
        manager.setServerConfig(config);
        manager.startServer();
        System.out.println("服务已启动。。。"+manager.getChannelFuture().channel());
        System.out.println(manager.getBootstrap().config());
//        manager.shutdownServer();
//        System.out.println(manager.isInitBootstrap());
//        manager.startServer();
    }
}
