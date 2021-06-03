package com.zhn.demo.baseweb.servlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/conn.ws")
public class WsConnServer {

    private final static String PING = "ping";

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[网页服务端监听]：新增一个连接，id：" + session.getId());

    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("[网页服务端监听]：来自会话: " + session.getId() + " 的信息: " + message);
        if (message.equals(PING)) session.getBasicRemote().sendText(message);

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("[网页服务端监听]：用户ID: " + session.getId() + " 离线");

    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("[网页服务端监听]：会话: " + session.getId() + " 发生异常");
        error.printStackTrace();
    }

}