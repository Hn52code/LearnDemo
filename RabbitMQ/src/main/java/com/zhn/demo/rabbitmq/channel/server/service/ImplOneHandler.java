package com.zhn.demo.rabbitmq.channel.server.service;


import com.zhn.demo.rabbitmq.channel.server.inout.RequestHandler;
import com.zhn.demo.rabbitmq.channel.server.inout.model.RequestModel;
import com.zhn.demo.rabbitmq.channel.server.inout.model.ResponseContent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ImplOneHandler extends RequestHandler {

    private ConcurrentHashMap<String, ReentrantReadWriteLock> map = new ConcurrentHashMap<>();

    @Override
    protected ResponseContent handle(RequestModel request) {
        System.out.println(System.currentTimeMillis()+"__"+request.toString());
        try {
            /* 模仿处理业务 耗时*/
            Thread.sleep((long) (Math.random() * 1000));
//            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseContent(0, "success", "ImplOne" + request.getParas().get("key"));
    }
}
