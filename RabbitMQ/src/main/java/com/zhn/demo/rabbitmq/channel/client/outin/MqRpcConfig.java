package com.zhn.demo.rabbitmq.channel.client.outin;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MqRpcConfig {

    @Value("${mq.callbackWorkQueue}")
    private String callbackWorkQueue;

    public String getCallbackWorkQueue() {
        return callbackWorkQueue;
    }

    @Bean
    public Queue pushQueue(){
        return new Queue(callbackWorkQueue);
    }

}
