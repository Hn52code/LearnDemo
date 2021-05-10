package com.zhn.demo.rabbitmq.example.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @program: RabbitMQDemo
 * @description: rabbitmq发送方
 * @author: Mr.ZhouHN
 * @create: 2018-05-02 16:35
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false,
                false, null);
        String msg = "Hello World !!!";
        channel.basicPublish("", QUEUE_NAME,null, msg.getBytes("utf-8"));
        System.out.println("[x] send :" + msg);
        channel.close();
        connection.close();
    }

}
