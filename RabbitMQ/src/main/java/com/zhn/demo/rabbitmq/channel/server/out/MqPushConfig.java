package com.zhn.demo.rabbitmq.channel.server.out;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MqPushConfig {

    @Value("${mq.serverPushExchange}")
    private String serverPushExchange;

    public String getServerPushExchange() {
        return serverPushExchange;
    }

    @Bean
    public Exchange pushExchange() {
        return new FanoutExchange(serverPushExchange);
    }
}
