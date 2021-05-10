package com.zhn.demo.rabbitmq.channel.server.inout;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MqCallbackConfig {

    @Value("${mq.callbackWorkQueue}")
    private String callbackWorkQueue;
    @Autowired
    private ConnectionFactory factory;

    /* rpc队列 */
    @Bean
    public Queue callbackQueue() {
        return new Queue(callbackWorkQueue, true);
    }

    /* 监听容器接收rpc请求 */
    @Bean
    public SimpleMessageListenerContainer replyMessage() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        container.setQueues(callbackQueue());
        container.setMessageListener(messageListenerAdapter());
        return container;
    }


    /*监听适配器 */
    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(new CallbackMessageReceiver());
        adapter.setMessageConverter(jackson2JsonMessageConverter());
        // 配置指定消息处理方法;此处注释，则选着默认方法handleMessage()
        adapter.setDefaultListenerMethod("onMessage");
        return adapter;
    }

    /* jackson消息转换器 */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
