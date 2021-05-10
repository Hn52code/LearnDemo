package com.zhn.demo.rabbitmq.channel.client.in;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MqReceiveInConfig {

    @Value("${mq.serverPushQueue}")
    private String serverPushQueue;
    @Value("${mq.serverPushExchange}")
    private String serverPushExchange;

    @Autowired
    private ConnectionFactory factory;

    /* 消费队列 */
    @Bean
    private Queue queue() {
        return new Queue(serverPushQueue, true);
    }

    /* 创建交换机 */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(serverPushExchange);
    }

    /* 向指定交换机绑定消费队列 */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    /* 消息监听容器 */
    @Bean
    public SimpleMessageListenerContainer receiveMessage() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        container.setQueues(queue());
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    /* 消息监听适配器 */
    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(new ReportMessageReceiver());
        // 配置指定消息处理方法;此处注释，则选着默认方法handleMessage()
        adapter.setDefaultListenerMethod("onMessage");
        adapter.setMessageConverter(jackson2JsonMessageConverter());
        return adapter;
    }

    /* jackson消息转换器 */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
