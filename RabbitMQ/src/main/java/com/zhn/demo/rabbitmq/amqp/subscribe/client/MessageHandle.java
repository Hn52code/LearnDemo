package com.zhn.demo.rabbitmq.amqp.subscribe.client;

import java.util.LinkedHashMap;

public class MessageHandle {

    public void onMessage(byte[] msg) {
        System.out.println(new String(msg));
    }


    public void onMessage(LinkedHashMap msg) {
        System.out.println(msg.toString());
    }
}
