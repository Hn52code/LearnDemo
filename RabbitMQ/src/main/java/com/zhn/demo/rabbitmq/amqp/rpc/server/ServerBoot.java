package com.zhn.demo.rabbitmq.amqp.rpc.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerBoot {

    public static void main(String[] args) {

        // new ClassPathXmlApplicationContext("mq-server-rpc.xml");
        new AnnotationConfigApplicationContext(ServerConfig.class);
    }
}
