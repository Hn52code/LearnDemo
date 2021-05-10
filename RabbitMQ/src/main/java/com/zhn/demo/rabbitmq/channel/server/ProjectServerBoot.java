package com.zhn.demo.rabbitmq.channel.server;

import com.zhn.demo.rabbitmq.channel.server.out.MqPushService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ProjectServerBoot {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mq-channel-s.xml");
        MqPushService bean = context.getBean(MqPushService.class);
        Map map = new HashMap();
        map.put("name", "这是名称");
        map.put("desc", "这是描述");
        bean.push(map);
    }

}
