package com.zhn.demo.rabbitmq.channel.client.outin;

import com.zhn.demo.rabbitmq.channel.JacksonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class MqRpcService {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    @Autowired
    private MqRpcConfig mqCallConfig;
    @Autowired
    private RabbitTemplate template;

    public ResponseContent invokeMethod(Map<String, Object> map) {
//        readWriteLock.writeLock().lock();
        ResponseContent responseContent = null;
//        try {
        responseContent = new ResponseContent();
            Message message = callRemote(map);
            if (message == null) {
                responseContent.setErrno(-1);
                responseContent.setError("rabbitmq invoke time out.");
            } else {
                responseContent.setErrno(0);
                responseContent.setError("rabbitmq invoke success.");
                responseContent.setData(new String(message.getBody(), Charset.defaultCharset()));
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            readWriteLock.writeLock().unlock();
//        }
        return responseContent;
    }

    private Message callRemote(Map<String, Object> map) {
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        String callStr = JacksonUtil.asJsonString(map);
        byte[] callBytes = callStr == null ? new byte[0] :
                callStr.getBytes(Charset.defaultCharset());
        return template.sendAndReceive(mqCallConfig.getCallbackWorkQueue(),
                new Message(callBytes, properties));
    }

}
