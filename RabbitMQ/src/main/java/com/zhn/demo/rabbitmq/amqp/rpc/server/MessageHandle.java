package com.zhn.demo.rabbitmq.amqp.rpc.server;

import com.zhn.demo.rabbitmq.amqp.rpc.Response;
import com.zhn.demo.rabbitmq.amqp.rpc.Request;

public class MessageHandle {

    public void onMessage(byte[] msg) {

    }

    public Response onMessage(Request request) {
        System.out.println(request.toString());
        try {
//            int waitTime = request.getWaitTime();
            int waitTime = (int) (Math.random() * 5) + 1;
            Thread.sleep(waitTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response response = new Response();
        response.setReqId(request.getReqId());
        response.setDiffValue(response.getDateTime().compareTo(request.getDateTime()));
        response.setReqTime(request.getDateTime());
        return response;
    }

}
