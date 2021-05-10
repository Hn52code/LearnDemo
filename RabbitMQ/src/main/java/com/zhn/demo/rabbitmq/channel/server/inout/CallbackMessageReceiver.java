package com.zhn.demo.rabbitmq.channel.server.inout;

import com.zhn.demo.rabbitmq.channel.SpringContextUtil;

import java.util.LinkedHashMap;

public class CallbackMessageReceiver {

    /* 此处调用的是本方法 */
    /* 需请求端设置 Message 对象的属性 ContentType:application/json */
    public Object onMessage(LinkedHashMap<String, Object> map) {
        return SpringContextUtil.getBean(MessageHandleCenter.class).resolve(map);
    }

    /* 需请求端设置 Message 对象的属性 __type__ : java.util.String */
    public Object onMessage(String str) {
        return SpringContextUtil.getBean(MessageHandleCenter.class).resolve(str);
    }

}
