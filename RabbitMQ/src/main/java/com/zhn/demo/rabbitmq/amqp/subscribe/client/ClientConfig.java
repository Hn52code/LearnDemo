package com.zhn.demo.rabbitmq.amqp.subscribe.client;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

import static com.zhn.demo.rabbitmq.amqp.Config.EXCHANGE;
import static com.zhn.demo.rabbitmq.amqp.Config.QUEUE;


@Configuration
public class ClientConfig {

    @Resource
    private ConnectionFactory factory;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange());
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueues(queue());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        MessageListenerAdapter adapter =  new MessageListenerAdapter();
        adapter.setDelegate(new MessageHandle());
        adapter.setDefaultListenerMethod("onMessage");
        adapter.setMessageConverter(messageConverter());
        return adapter;
    }

    @Bean
    public MessageConverter messageConverter(){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        converter.se
        return converter;
    }


}
