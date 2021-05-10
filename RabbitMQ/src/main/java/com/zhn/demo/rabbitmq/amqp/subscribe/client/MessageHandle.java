package com.zhn.demo.rabbitmq.amqp.subscribe.client;

public class MessageHandle {

    public void onMessage(byte[] msg) {
        System.out.println(new String(msg));
    }
}
