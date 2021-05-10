package com.zhn.demo.rabbitmq.amqp.reqp.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerBoot {

    public static void main(String[] args) {

        new ClassPathXmlApplicationContext("mq-server-rpc.xml");
    }
}
