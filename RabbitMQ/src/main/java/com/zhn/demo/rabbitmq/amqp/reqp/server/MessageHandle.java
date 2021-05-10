package com.zhn.demo.rabbitmq.amqp.reqp.server;

import com.zhn.demo.rabbitmq.amqp.reqp.RepMessage;
import com.zhn.demo.rabbitmq.amqp.reqp.ReqMessage;

import java.lang.reflect.Field;

public class MessageHandle {

    public void onMessage(byte[] msg){

    }

    public RepMessage onMessage(ReqMessage msg){
        Field[] fields = msg.getClass().getDeclaredFields();
        for (Object o: fields)
            System.out.println(o);
        RepMessage rep = new RepMessage();
        rep.setCode("200");
        rep.setId(msg.getId());
        rep.setResult("ok");
        return rep;
    }

}
