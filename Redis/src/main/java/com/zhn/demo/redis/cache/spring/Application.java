package com.zhn.demo.redis.cache.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class Application {

    public static void main(String[] args) {
        // 配置方式
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("name", "配置");
        Object value = redisTemplate.opsForValue().get("name");
        System.out.println(value);

        // 注解方式
        AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate2 = context1.getBean(RedisTemplate.class);
        redisTemplate2.opsForValue().set("name", "注解");
        Object value2 = redisTemplate2.opsForValue().get("name");
        System.out.println(value2);
    }
}
