package com.zhn.demo.rabbitmq.amqp.rpc.server;

import com.zhn.demo.rabbitmq.amqp.rpc.Request;
import com.zhn.demo.rabbitmq.amqp.rpc.Response;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageHandle {

    private AtomicInteger count = new AtomicInteger(0);

    public void onMessage(byte[] msg) {

    }

    public Response onMessage(Request request) {
        System.out.println(request.toString() + "__" + count.incrementAndGet());
        try {
//            int waitTime = (int) (Math.random() * 3) + 1;
            Thread.sleep(2* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response response = new Response();
        response.setReqId(request.getReqId());
        response.setCode(200);
        response.setResult("success");
        response.setReqTime(request.getDateTime());
        return response;
    }

}
