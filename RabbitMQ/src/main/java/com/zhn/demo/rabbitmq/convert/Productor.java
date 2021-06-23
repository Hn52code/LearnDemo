package com.zhn.demo.rabbitmq.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Productor {

    public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Customer.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        Person person = new Person();
        person.setName("zhou");
        person.setAge(25);
        person.setBirthday(new Date());
        System.out.println(person.toString());

        //-----------------------------------------------
        // 1.0 生产端未指定contentType时，消费端依然是byte[]接收
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);
//        rabbitTemplate.convertAndSend("", "customer", json);

        //-----------------------------------------------
//        // 2.0 生产端指定contentType时，消费端则需要map（具体的是LinkedHashMap），否则本端报错
//        MessageProperties properties = new MessageProperties();
//        properties.setContentType("application/json");
//        Message message = new Message(json.getBytes(), properties);
//        rabbitTemplate.convertAndSend("", "customer", message);

        //-----------------------------------------------
//        // 3.0 生产端指定contentType且指定 集合，消费端接收需要List，否则本端报错
//        MessageProperties properties2 = new MessageProperties();
//        properties2.setContentType("application/json");
//        List<Person> list2 = new ArrayList<>();
//        list2.add(person);
//        list2.add(person);
//        String json2 = mapper.writeValueAsString(list2);
//        Message message2 = new Message(json2.getBytes(), properties2);
//        rabbitTemplate.convertAndSend("", "customer", message2);

        //-----------------------------------------------
//        // 4.0 生产端指定contentType且设置实体类，消费端则需要指定实体类接收，否则本端报错
//        MessageProperties properties3 = new MessageProperties();
//        properties3.setContentType("application/json");
//        properties3.getHeaders().put("__TypeId__", "com.zhn.demo.rabbitmq.mq4javabean.Person");
//        Message message3 = new Message(json.getBytes(), properties3);
//        rabbitTemplate.convertAndSend("", "customer", message3);

        //-----------------------------------------------
        // 4.0改进 生产端指定contentType且设置实体类简称，消费端则需要指定实体类接收，否则本端报错
//        MessageProperties properties4 = new MessageProperties();
//        properties4.setContentType("application/json");
//        properties4.getHeaders().put("__TypeId__", "person");
//        Message message4 = new Message(json.getBytes(), properties4);
//        rabbitTemplate.convertAndSend("", "customer", message4);

        //-----------------------------------------------
        // 5.0 生产端指定contentType且指定 实体类集合，消费端则需要指定实体类集合对象接收，否则本端报错
//        List<Person> list5 = new ArrayList<>();
//        list5.add(person);
//        list5.add(person);
//        MessageProperties properties5 = new MessageProperties();
//        properties5.setContentType("application/json");
//        properties5.getHeaders().put("__TypeId__", "java.util.List");
//        properties5.getHeaders().put("__ContentTypeId__", "person");
//        String json5 = mapper.writeValueAsString(list5);
//        Message message5 = new Message(json5.getBytes(), properties5);
//        rabbitTemplate.convertAndSend("", "customer", message5);

        //-----------------------------------------------
        // 6.0 生产端指定contentType且指定 实体类Map ，消费端则需要指定实体类Map接收，否则本端报错
//        Map<String, Person> map6 = new HashMap<>();
//        map6.put("key1", person);
//        map6.put("key2", person);
//        MessageProperties properties6 = new MessageProperties();
//        properties6.setContentType("application/json");
//        properties6.getHeaders().put("__TypeId__", "java.util.Map");
//        properties6.getHeaders().put("__KeyTypeId__", "java.lang.String");
//        properties6.getHeaders().put("__ContentTypeId__", "person");
//        String json6 = mapper.writeValueAsString(map6);
//        Message message6 = new Message(json6.getBytes(), properties6);
//        rabbitTemplate.convertAndSend("", "customer", message6);

        /* 自定义的实体类中包含了List和Map等数据类型，消费端接收也仅仅是需要该自定义的实体类 */
//        DataPackage dataPackage = new DataPackage();
//        dataPackage.setDevId("1023123");
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "zhou");
//        map.put("age", 1);
//        map.put("birthday", new Date());
//        dataPackage.setContent(map);
//        List<Object> list = new ArrayList<>();
//        list.add(person);
//        list.add(person);
//        dataPackage.setList(list);
//        String json7 = mapper.writeValueAsString(dataPackage);
//        MessageProperties properties7 = new MessageProperties();
//        properties7.setContentType("application/json");
//        properties7.getHeaders().put("__TypeId__", "dataPackage");
//        Message message7 = new Message(json7.getBytes(), properties7);
//        rabbitTemplate.convertAndSend("", "customer", message7);
        Data data = new Data();
        data.setUuid(UUID.randomUUID());
        data.setAttr("one");
        data.setType(Type.LOGIN);
        ContentImpl1 content = new ContentImpl1();
        content.setGateWay("123456789");
        content.setPub("pub");
        data.setContent(content);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        messageProperties.getHeaders().put("__TypeId__", "data");
        String json8 = mapper.writeValueAsString(data);
        Message message8 = new Message(json8.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("", "customer", message8);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /* 收发模板 */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setReplyTimeout(20000);
        template.setReceiveTimeout(20000);
        return template;
    }
}
