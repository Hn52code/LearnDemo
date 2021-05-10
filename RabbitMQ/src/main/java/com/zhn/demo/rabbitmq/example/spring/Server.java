package com.zhn.demo.rabbitmq.example.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @program: RabbitMQDemo
 * @description:
 * @author: Mr.ZhouHN
 * @create: 2018-05-04 17:20
 */
public class Server {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("spring-mq.xml");
        AmqpTemplate amqpTemplate = applicationContext.getBean(AmqpTemplate.class);

        amqpTemplate.convertAndSend("myExchange","a.b","hello".getBytes());

    }
}
