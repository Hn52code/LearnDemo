package com.zhn.demo.rabbitmq.channel.server.service;


import com.zhn.demo.rabbitmq.channel.server.inout.RequestHandler;
import com.zhn.demo.rabbitmq.channel.server.inout.model.RequestModel;
import com.zhn.demo.rabbitmq.channel.server.inout.model.ResponseContent;
import org.springframework.stereotype.Component;

@Component
public class ImplTwoHandler extends RequestHandler {

    @Override
    protected ResponseContent handle(RequestModel request) {
        System.out.println(System.currentTimeMillis() + "__" + request.toString());
        return new ResponseContent(0, "success", "ImplTwo");
    }
}
