package com.zhn.demo.rabbitmq.amqp.subscribe.server;

import com.zhn.demo.rabbitmq.amqp.Config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerBoot {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mq-server.xml");

        AmqpTemplate template = context.getBean(RabbitTemplate.class);

        template.convertAndSend(Config.EXCHANGE,"","nihao ");

    }

}
