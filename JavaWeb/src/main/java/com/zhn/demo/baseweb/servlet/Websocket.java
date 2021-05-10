package com.zhn.demo.baseweb.servlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
public class Websocket {

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。此处使用原子类
    // private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，可通过它向客户端发送数据
     * @param config  配置，包含连接的信息。
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {

        // 获取httpsession对象，获得登录信息。
        // HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        System.out.println("[网页服务端监听]：新增一个连接，id：" + session.getId() + " 目前连接总数：");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {


        System.out.println("[网页服务端监听]：来自会话: " + session.getId() + " 的信息: " + message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("[网页服务端监听]：用户ID: " + session.getId() + " 离线，目前在线人数：");

    }

    /**
     * 发生错误时调用
     *
     * @param session 会话
     * @param error   错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("[网页服务端监听]：会话: " + session.getId() + " 发生异常");
        error.printStackTrace();
    }

}