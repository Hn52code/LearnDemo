package com.zhn.demo.baseweb.servlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wsconnect.ws")
public class WsConnection {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {

        System.out.println("[网页服务端监听]：新增一个连接，id：" + session.getId() + " 目前连接总数：");
    }

    @OnMessage
    public void onMessage(String message, Session session) {


        System.out.println("[网页服务端监听]：来自会话: " + session.getId() + " 的信息: " + message);
    }

    @OnClose
    public void onClose(Session session) {

        System.out.println("[网页服务端监听]：用户ID: " + session.getId() + " 离线，目前在线人数：");
    }

    @OnError
    public void onError(Session session, Throwable error) {

        error.printStackTrace();
        System.out.println("[网页服务端监听]：会话: " + session.getId() + " 发生异常");
    }

}