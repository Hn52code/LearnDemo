package com.zhn.demo.rabbitmq.amqp.reqp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhn.demo.rabbitmq.amqp.Config;
import com.zhn.demo.rabbitmq.amqp.reqp.RepMessage;
import com.zhn.demo.rabbitmq.amqp.reqp.ReqMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

public class Boot {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("mq-client-rpc.xml");

        RabbitTemplate template = context.getBean(RabbitTemplate.class);

        ReqMessage req = new ReqMessage();
        req.setCom("复位");
        req.setDate(new Date());
        req.setEid("1234");
        req.setId(UUID.randomUUID().toString());

        ObjectMapper mapper = new ObjectMapper();
        String reqStr = mapper.writeValueAsString(req);


        MessageProperties properties = new MessageProperties();

        properties.setContentType("application/json");
        properties.getHeaders().put("__TypeId__", "ReqMessage");
        properties.setCorrelationId(UUID.randomUUID().toString());


        Message message = new Message(reqStr.getBytes(), properties);

        template.setMessageConverter(new Jackson2JsonMessageConverter());
        RepMessage rep = template.convertSendAndReceiveAsType(Config.QUEUE_RPC, message,
                new ParameterizedTypeReference<RepMessage>() {
                });
        Field[] fields = rep.getClass().getDeclaredFields();
        for (Object o : fields) {
            System.out.println(o);
        }

    }
}
