package com.zhn.demo.rabbitmq.channel.server.out;

import com.zhn.demo.rabbitmq.channel.JacksonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class MqPushService {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private MqPushConfig mqPushConfig;

    public void push(Object messageBody) {
        // 将消息转化为json
        String jsonStr = JacksonUtil.asJsonString(messageBody);
        // 创建消息头，并设置属性
        MessageProperties properties = new MessageProperties();
        // 设置数据格式为json
        properties.setContentType("application/json");
        // 打包消息对象
        byte[] bytes = jsonStr == null ? new byte[0] : jsonStr.getBytes(Charset.defaultCharset());
        Message message = new Message(bytes, properties);
        // 发送消息
        template.convertAndSend(mqPushConfig.getServerPushExchange(), "", message);
    }

    /* *
     *  1. MessageProperties 接收端的onMessage方法参数使用 byte[] 方法接收
     *  2. 设置 properties.setContentType("application/json");时，接收端 onMessage方法LinkedHashMap 参数
     *  3. 如果发送端的消息是list转换的json str 接收端将使用List位参数。
     *  4. 当发送端发送是一个javabean对象转化的时，而接收端想设置接收类型为该类，此时需要在接收端设置接收的指定类
     */

}
