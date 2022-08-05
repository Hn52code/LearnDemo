package com.zhn.demo.rabbitmq.amqp.rpc.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zhn.demo.rabbitmq.amqp.Constant;
import com.zhn.demo.rabbitmq.amqp.rpc.Request;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.DirectReplyToMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {

//    @Resource
//    private ConnectionFactory factory;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri("amqp://admin:admin@localhost:5672");
//        factory.setChannelCacheSize(25);
//        factory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
//        factory.setConnectionLimit(100);
        factory.setChannelCheckoutTimeout(10000);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }

    @Bean
    public Queue queue() {
        return new Queue(Constant.QUEUE_RPC);
    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory factory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(factory);
//        container.setQueues(queue());
//        /* 设置通道预处理数 */
//        container.setPrefetchCount(10);
//        /* 设置并发数通道 */
//        container.setConcurrentConsumers(5);
//        /* 设置最大允许的消费者 */
//        container.setMaxConcurrentConsumers(100);
//        /* 最小启动新消费者的间隔时间 此处2s 默认10s*/
//        container.setStartConsumerMinInterval(2);
//        /* 最小关闭消费者的间隔时间，此处设置5s 默认1min*/
//        container.setStopConsumerMinInterval(5000);
//        /* 设置触发活跃的间隔时间 默认10s*/
//        container.setConsecutiveActiveTrigger(10);
//        /* 设置触发闲置的间隔时间, 默认10s*/
//        container.setConsecutiveIdleTrigger(10);
//        /* 消息确认 */
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener(messageListenerAdapter());
//        return container;
//    }


    @Bean
    public DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(ConnectionFactory factory) {
        DirectRabbitListenerContainerFactory  container = new DirectRabbitListenerContainerFactory ();
        container.setConnectionFactory(factory);
        container.setConsumersPerQueue(5);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageConverter(messageConverter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDefaultListenerMethod("onMessage");
        adapter.setDelegate(new MessageHandle());
        adapter.setMessageConverter(messageConverter());
        return adapter;
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
//        module.addSerializer(LocalDate.class, new LocalDateSerializer(dataFormatter));
//        module.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
//        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dataFormatter));
//        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        objectMapper.registerModule(module);
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        Map<String, Class<?>> map = new HashMap<>();
        map.put("Request", Request.class);
        DefaultJackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper();
        mapper.setIdClassMapping(map);
        converter.setClassMapper(mapper);
        return converter;
    }


}
