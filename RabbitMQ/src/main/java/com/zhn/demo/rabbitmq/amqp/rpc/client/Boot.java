package com.zhn.demo.rabbitmq.amqp.rpc.client;

import com.sun.org.apache.regexp.internal.RE;
import com.zhn.demo.rabbitmq.amqp.Constant;
import com.zhn.demo.rabbitmq.amqp.rpc.JsonUtil;
import com.zhn.demo.rabbitmq.amqp.rpc.Request;
import com.zhn.demo.rabbitmq.amqp.rpc.Response;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Boot {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /* xml配置方式且未设置调优参数*/
        // demo1();
        /* 注解方式设置并发参数 */
         demo2();
        /* 异步方式------> 示例测试失败 有时间在测试*/
//        demo3();
    }


    private static void demo1() throws InterruptedException {
        //        /* 初始化配置文件 */
        ApplicationContext context = new ClassPathXmlApplicationContext("mq-client-rpc.xml");

        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        template.setMessageConverter(new Jackson2JsonMessageConverter(JsonUtil.getMapper()));
        template.setReplyTimeout(8000);
        template.setReceiveTimeout(8000);
        template.setUserCorrelationId(true);
//      template.setUseTemporaryReplyQueues(true);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                /* 创建请求消息 */
                Request request = new Request();
                CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
                request.setReqId(correlationData.getId());
                MessageProperties properties = new MessageProperties();

                properties.setCorrelationId(correlationData.getId());
//                properties.setExpiration("8000");
                properties.setContentType("application/json");
                properties.getHeaders().put("__TypeId__", "Request");

                String reqStr = JsonUtil.parse(request);
                Message message = new Message(reqStr.getBytes(), properties);
                System.out.println("start: " + request.getReqId());
                // 请求消响应结果
//                Response response = template.convertSendAndReceiveAsType(Constant.QUEUE_RPC, message,
//                        correlationData, new ParameterizedTypeReference<Response>() {
//                        });
                Message response = template.sendAndReceive(Constant.QUEUE_RPC, message);
                System.out.println(response.toString());
                System.out.println("end: " + request.getReqId());
            }).start();
            Thread.sleep(1000);
        }
    }

    private static void demo2() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                RabbitTemplate template = context.getBean(RabbitTemplate.class);
                String conId = UUID.randomUUID().toString();
                Request request = new Request();
                request.setReqId(conId);
                request.setContent("hello rpc server");
                String body = JsonUtil.parse(request);

                MessageProperties properties = new MessageProperties();
                properties.setCorrelationId(conId);
                properties.setContentType("application/json");
                properties.getHeaders().put("__TypeId__", "Request");
                Message message = new Message(body.getBytes(), properties);

                Response response = template.convertSendAndReceiveAsType(Constant.QUEUE_RPC, message,
                        new ParameterizedTypeReference<Response>() {
                        });
                System.out.println(response.toString());
//        Object receive = template.convertSendAndReceive(Constant.QUEUE_RPC, message);
//        Message r = template.sendAndReceive(Constant.QUEUE_RPC, message);
            }).start();
            Thread.sleep(100);
        }
    }

//    private static void demo3() throws InterruptedException, ExecutionException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        AsyncRabbitTemplate template = context.getBean(AsyncRabbitTemplate.class);
//
//        String id = UUID.randomUUID().toString();
//        Request request = new Request();
//        request.setReqId(id);
//        request.setContent("hello rpc server");
//        MessageProperties properties = new MessageProperties();
//        properties.setCorrelationId(id);
//        properties.setContentType("application/json");
//        properties.getHeaders().put("__TypeId__", "Request");
//
//        String parse = JsonUtil.parse(request);
//
//        Message message = new Message(parse.getBytes(), properties);
//
//        ParameterizedTypeReference<Response> type = new ParameterizedTypeReference<Response>() {
//        };
//        AsyncRabbitTemplate.RabbitConverterFuture<Response> future = template.convertSendAndReceiveAsType(Constant.QUEUE_RPC, message, type);
//        Response response = future.get();
//        System.out.println(response);
//    }

//    private static Message wrap(Request request) {
//        // 封装请求消息
//        MessageProperties properties = new MessageProperties();
//        properties.setCorrelationId(UUID.randomUUID().toString());
//        properties.setContentType("application/json");
//        properties.getHeaders().put("__TypeId__", "Request");
//        String reqStr = JsonUtil.parse(request);
//        return new Message(reqStr.getBytes(), properties);
//    }

}
