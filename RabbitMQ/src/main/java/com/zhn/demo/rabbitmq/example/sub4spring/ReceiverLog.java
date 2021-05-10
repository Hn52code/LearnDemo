package com.zhn.demo.rabbitmq.example.sub4spring;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ReceiverLog {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RabbitMqClient.class);

        ConnectionFactory factory = context.getBean(ConnectionFactory.class);

        RabbitAdmin admin = context.getBean(RabbitAdmin.class);

        Queue queue = QueueBuilder.durable("fanout.queue").autoDelete().build();
        System.out.println(queue);
        Exchange exchange = ExchangeBuilder.directExchange("fanout").build();
        System.out.println(exchange);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("").noargs();
        System.out.println(binding);

        admin.declareQueue(queue);
        admin.declareExchange(exchange);
        admin.declareBinding(binding);

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        container.setQueues(queue);
        container.setMessageListener(message -> System.out.println(new String(message.getBody())));
    }
}
