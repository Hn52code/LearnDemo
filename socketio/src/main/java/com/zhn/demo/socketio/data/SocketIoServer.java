package com.zhn.demo.socketio.data;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.protocol.JacksonJsonSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class SocketIoServer {

    @Value("${socketio.host}")
    private String host;
    @Value("${socketio.port}")
    private int port;
    @Value("${socketio.namespace}")
    private String namespace;
    @Autowired
    private SocketIOClientMap socketIOClientMap;

    private static SocketIOServer server;

    @PostConstruct
    public void startSocketIoServer() {
        // 创建SocketIoServer
        server = new SocketIOServer(configuration());
        // 添加心跳事件监听器
        // server.addPingListener(pingListener());
        // 添加连接事件监听器
        server.addConnectListener(connectListener());
        // 添加断开事件监听器
        server.addDisconnectListener(disconnectListener());
        // 添加客户端消息的接收监听器( 字符串)
        server.addEventListener("client-auth", Login.class, dataListener());
        // 添加事件拦截器
        server.addEventInterceptor(eventInterceptor());
        // 启动socket-io服务（异步的）
        server.start();
    }

    @PreDestroy
    private void stopSocketIoServer() {
        server.stop();
    }

    private DataListener<Login> dataListener() {
        return (client, data, ackRequest) -> {
//            JSONObject jsonObject = JSONObject.parseObject(data);
//            System.out.println(jsonObject.getInteger("ID"));
            socketIOClientMap.put(data.getId(), client);
            System.out.println("客户端: " + client.getSessionId() + "data: " + data.getId());

            // 回调回复连接的客户端
            ackRequest.sendAckData("您已连接成功！");
        };
    }

    private ConnectListener connectListener() {
        return client -> System.out.println("connect " + client.getSessionId());
    }

    private DisconnectListener disconnectListener() {
        return client -> {
            System.out.println("disconnect " + client.getSessionId());
            socketIOClientMap.del(client);
        };
    }

    private PingListener pingListener() {
        return client -> System.out.println("ping " + client.getSessionId());
    }

    private EventInterceptor eventInterceptor() {
        return (namespaceClient, s, list, ackRequest) -> {
            System.out.println(s);
        };
    }

    private ExceptionListener exceptionListener() {
        return new ExceptionListenerAdapter() {
            @Override
            public void onEventException(Exception e, List<Object> data, SocketIOClient client) {
                System.out.println("event exception: ");
                e.printStackTrace();
            }
        };
    }

    public SocketIOServer getServer() {
        return server;
    }

    public Configuration getSocketIoServerConfig() {
        if (server == null) return null;
        return server.getConfiguration();
    }

    private Configuration configuration() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        // 设置鉴权验证
        // config.setAuthorizationListener(handshakeData -> false);
        config.setJsonSupport(new JacksonJsonSupport());
        // 设置异常监听器
        config.setExceptionListener(exceptionListener());
        return config;
    }
}
