package com.zhn.demo.baseweb.servlet.chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/chat.ws")
public class WsChatServer {

    private final static String PING = "ping";

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("[网页服务端监听]：新增一个连接，id：" + session.getId() + " 目前连接总数：");

    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("[网页服务端监听]：来自会话: " + session.getId() + " 的信息: " + message);
        if (message.equals(PING)) {
            session.getBasicRemote().sendText(PING);
            return;
        }
        if (message.contains("\"dataType\":\"auth\"")) {
            // 鉴权相关
            AuthData auth = DataConverter.getConverter().readValue(message, AuthData.class);


        } else if (message.contains("\"dataType\":\"group\"")) {
            // 群相关
            GroupData group = DataConverter.getConverter().readValue(message, GroupData.class);


        } else if (message.contains("\"dataType\":\"msg\"")) {
            // 消息相关
            MsgData msg = DataConverter.getConverter().readValue(message, MsgData.class);


        }

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("[网页服务端监听]：用户ID: " + session.getId() + " 离线，目前在线人数：");

    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("[网页服务端监听]：会话: " + session.getId() + " 发生异常");
        error.printStackTrace();

    }

}