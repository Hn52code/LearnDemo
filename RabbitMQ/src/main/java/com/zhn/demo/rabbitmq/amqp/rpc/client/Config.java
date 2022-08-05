package com.zhn.demo.rabbitmq.amqp.rpc.client;

import com.zhn.demo.rabbitmq.amqp.rpc.JsonUtil;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri("amqp://admin:admin@localhost:5672");
//        factory.setChannelCacheSize(10);
        factory.setChannelCheckoutTimeout(3);
        factory.setConnectionLimit(10);
//        factory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    /* demo失败，有时间再测试 */
//    @Bean
//    public AsyncRabbitTemplate rabbitAdmin2(RabbitTemplate template) {
//        return new AsyncRabbitTemplate(template);
//    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        /* 设置为true后，必须在发送的消息中 设置 CorrelationId */
        template.setUserCorrelationId(true);
        /* 回复超时时间 */
        template.setReplyTimeout(10000);
        /* 设置消息转换器 */
        template.setMessageConverter(new Jackson2JsonMessageConverter(JsonUtil.getMapper()));
        return template;
    }

}
