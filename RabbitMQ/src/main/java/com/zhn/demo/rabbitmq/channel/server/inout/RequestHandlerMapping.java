package com.zhn.demo.rabbitmq.channel.server.inout;

import com.zhn.demo.rabbitmq.channel.SpringContextUtil;
import com.zhn.demo.rabbitmq.channel.server.inout.model.RequestModel;

public class RequestHandlerMapping {

    static RequestHandler getHandler(RequestModel request) {
        return (RequestHandler) SpringContextUtil.getBean(request.getUri() + "Handler");
    }

}
