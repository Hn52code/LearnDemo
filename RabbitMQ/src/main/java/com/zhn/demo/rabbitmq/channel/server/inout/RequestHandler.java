package com.zhn.demo.rabbitmq.channel.server.inout;

import com.zhn.demo.rabbitmq.channel.server.inout.model.RequestModel;
import com.zhn.demo.rabbitmq.channel.server.inout.model.ResponseContent;

public abstract class RequestHandler {

    protected abstract ResponseContent handle(RequestModel request);

}
