package com.zhn.demo.rabbitmq.amqp.rpc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhn.demo.rabbitmq.amqp.Constant;
import com.zhn.demo.rabbitmq.amqp.rpc.JsonUtil;
import com.zhn.demo.rabbitmq.amqp.rpc.Request;
import com.zhn.demo.rabbitmq.amqp.rpc.Response;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;

import java.security.MessageDigest;
import java.util.UUID;

public class Boot {

    public static void main(String[] args) throws InterruptedException {

//        /* 初始化配置文件 */
//        ApplicationContext context = new ClassPathXmlApplicationContext("mq-client-rpc.xml");
//
//        RabbitTemplate template = context.getBean(RabbitTemplate.class);
//        template.setMessageConverter(new Jackson2JsonMessageConverter(JsonUtil.getMapper()));
//        template.setReplyTimeout(8000);
//        template.setReceiveTimeout(8000);
//        template.setUserCorrelationId(true);
//
////      template.setUseTemporaryReplyQueues(true);
//
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                /* 创建请求消息 */
//                Request request = new Request();
//                CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//                request.setReqId(correlationData.getId());
//                MessageProperties properties = new MessageProperties();
//
//                properties.setCorrelationId(correlationData.getId());
////                properties.setExpiration("8000");
//                properties.setContentType("application/json");
//                properties.getHeaders().put("__TypeId__", "Request");
//
//                String reqStr = JsonUtil.parse(request);
//                Message message = new Message(reqStr.getBytes(), properties);
//                System.out.println("start: " + request.getReqId());
//                // 请求消响应结果
////                Response response = template.convertSendAndReceiveAsType(Constant.QUEUE_RPC, message,
////                        correlationData, new ParameterizedTypeReference<Response>() {
////                        });
//                Message response = template.sendAndReceive(Constant.QUEUE_RPC, message);
//                System.out.println(response.toString());
//                System.out.println("end: " + request.getReqId());
//            }).start();
//            Thread.sleep(1000);
//        }

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        String body = JsonUtil.parse("");
        MessageProperties properties = new MessageProperties();
        Message message = new Message(body.getBytes(), properties);
        ParameterizedTypeReference<Response> type = new ParameterizedTypeReference<Response>() {
        };
        Response response = template.convertSendAndReceiveAsType(Constant.QUEUE_RPC, message, type);
        Object receive = template.convertSendAndReceive(Constant.QUEUE_RPC, message);
        Message r = template.sendAndReceive(Constant.QUEUE_RPC, message);
    }

//    private static Message wrap(Request request) {
//        // 封装请求消息
//        MessageProperties properties = new MessageProperties();
//        properties.setCorrelationId(UUID.randomUUID().toString());
//        properties.setContentType("application/json");
//        properties.getHeaders().put("__TypeId__", "Request");
//        properties.getHeaders().put("__ContentTypeId__", "LocalDateTime");
//        properties.setCorrelationId(UUID.randomUUID().toString());
//        String reqStr = string(request);
//        return new Message(reqStr.getBytes(), properties);
//    }

}
