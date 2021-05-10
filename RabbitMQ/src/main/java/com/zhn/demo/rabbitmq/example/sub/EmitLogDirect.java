package com.zhn.demo.rabbitmq.example.sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class EmitLogDirect {

    // 定义交换机名称
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 设置交换机，以及类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 路由键routingKey
        String[] serveritys = {"debug", "info", "warning", "error"};
        String[] messages = {"This is a DEBUG message!", "This is a INFO message!", "This is a WARNING message!",
                "This is a ERROR message!"};

        for (int i = 0; i < serveritys.length; i++) {
            channel.basicPublish(EXCHANGE_NAME, serveritys[i], null, messages[i].getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + serveritys[i] + "':'" + messages[i] + "'");
        }

        channel.close();
        connection.close();
    }

}
