package com.zhn.demo.rabbitmq.example.sub4spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmitLog {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqClient.class);

        String targetExchange = "fanout";
        RabbitTemplate template = context.getBean(RabbitTemplate.class);


        Message message = MessageBuilder.withBody("hello fanout log".getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .build();

        template.convertAndSend(targetExchange, "", message);

    }

}
