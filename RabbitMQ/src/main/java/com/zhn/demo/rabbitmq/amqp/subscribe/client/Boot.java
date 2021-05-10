package com.zhn.demo.rabbitmq.amqp.subscribe.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Boot {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("mq-client.xml");
    }
}
