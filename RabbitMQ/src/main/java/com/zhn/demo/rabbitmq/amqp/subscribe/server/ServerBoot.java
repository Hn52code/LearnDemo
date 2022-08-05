package com.zhn.demo.rabbitmq.amqp.subscribe.server;

import com.zhn.demo.rabbitmq.amqp.Constant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServerBoot {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mq-server.xml");

        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        MessageProperties p = new MessageProperties();
        p.setHeader("Content-Type","application/json");
        Message message = new Message("heheh".getBytes(), p);

        template.convertAndSend(Constant.QUEUE,message);

    }

}
