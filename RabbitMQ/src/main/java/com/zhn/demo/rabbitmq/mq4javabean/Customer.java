package com.zhn.demo.rabbitmq.mq4javabean;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Customer {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Customer.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /* 收发模板 */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setReplyTimeout(20000);
        template.setReceiveTimeout(20000);
        return template;
    }

    @Bean
    public Queue createQueue(){
        return new Queue("customer");
    }


    @Bean
    public SimpleMessageListenerContainer listenerContainer() {

        /* 类映射 容器 */
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
//        idClassMapping.put("person", Person.class);
//        idClassMapping.put("dataPackage", DataPackage.class);
        idClassMapping.put("data", Data.class);
        classMapper.setIdClassMapping(idClassMapping);

        /*json 转换器 */
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper);

        /* 消息监听器适配器*/
        MessageListenerAdapter adapter = new MessageListenerAdapter(new ReplyMessageReceiver());
        adapter.setMessageConverter(converter);
        // 配置指定消息处理方法;此处注释，则选着默认方法handleMessage()
        adapter.setDefaultListenerMethod("onMessage");

        /* 一个简单的消息监听器容器 */
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setQueueNames("customer");
        container.setMessageListener(adapter);

        return container;
    }


}
