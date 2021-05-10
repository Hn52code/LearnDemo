package com.zhn.demo.rabbitmq.channel.client.in;

import com.zhn.demo.rabbitmq.channel.JacksonUtil;

import java.util.LinkedHashMap;

public class ReportMessageReceiver {

    public void onMessage(LinkedHashMap<String, Object> message) {
        String jsonStr = JacksonUtil.asJsonString(message);
        System.out.println(jsonStr);
    }

}
