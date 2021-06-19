package com.zhn.demo.redis.cache.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        // 连接释放 参考：https://www.cnblogs.com/a393060727/p/5281950.html

        // 配置方式
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        for (int i = 0; i < 800; i++) {
            new Thread(() -> {
                RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
                UUID uuid = UUID.randomUUID();
                System.out.println(uuid);
                int time = (int) ((Math.random() * 49) + 1);
                redisTemplate.opsForValue().set(uuid.toString(), "jbqjwebwqn", time, TimeUnit.SECONDS);
                Object value = redisTemplate.opsForValue().get(uuid.toString());
                System.out.print(uuid);

                try {
                    Thread.sleep((long) ((Math.random() * 99) + 1) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("   ->" + (i + 1));
        }

        while (true) Thread.sleep(10000);
        // 注解方式
//        AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(RedisConfig.class);
//        RedisTemplate redisTemplate2 = context1.getBean(RedisTemplate.class);
//        redisTemplate2.opsForValue().set("name", "注解");
//        Object value2 = redisTemplate2.opsForValue().get("name");
//        System.out.println(value2);
//
    }
}
