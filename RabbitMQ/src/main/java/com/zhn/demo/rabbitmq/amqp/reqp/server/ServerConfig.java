package com.zhn.demo.rabbitmq.amqp.reqp.server;

import com.zhn.demo.rabbitmq.amqp.Config;
import com.zhn.demo.rabbitmq.amqp.reqp.ReqMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {

    @Resource
    private ConnectionFactory factory;

    @Bean
    public Queue queue() {
        return new Queue(Config.QUEUE_RPC);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueues(queue());
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDefaultListenerMethod("onMessage");
        adapter.setDelegate(new MessageHandle());
        adapter.setMessageConverter(messageConverter());
        return adapter;
    }

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        Map<String, Class<?>> map = new HashMap<>();
        map.put("ReqMessage", ReqMessage.class);
        DefaultJackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper();
        mapper.setIdClassMapping(map);
        converter.setClassMapper(mapper);
        return converter;
    }


}
