package com.zhn.demo.rabbitmq.channel.server;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MqClient4SInitializer {

    @Value("${mq.port}")
    private int port;
    @Value("${mq.host}")
    private String host;
    @Value("${mq.username}")
    private String userName;
    @Value("${mq.password}")
    private String password;

    @Bean("mqConnectFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(host);
        factory.setPort(port);
        factory.setUsername(userName);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    /* 收发模板 */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setReceiveTimeout(20000);
        template.setReplyTimeout(20000);
        return template;
    }
}
