package com.zhn.demo.rabbitmq.channel.server.inout;

import com.zhn.demo.rabbitmq.channel.JacksonUtil;
import com.zhn.demo.rabbitmq.channel.server.inout.model.RequestModel;
import com.zhn.demo.rabbitmq.channel.server.inout.model.ResponseContent;
import com.zhn.demo.rabbitmq.channel.server.inout.model.ResponseModel;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class MessageHandleCenter {

    /* 调用的是本方法 */
    Object resolve(LinkedHashMap<String, Object> map) {
        System.out.println("handle map " + map);
        RequestModel request = JacksonUtil.mapToJavaObject(map, RequestModel.class);
        RequestHandler handler = RequestHandlerMapping.getHandler(request);
        ResponseContent resp = null;
        try {
            resp = handler.handle(request);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseContent(-2, "请求发送异常", null);
        }
        return new ResponseModel(request.getMethod(), request.getUri(), resp);
    }

    Object resolve(String str) {
        System.out.println("handle string " + str);
        return null;
    }

}
