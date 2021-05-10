package com.zhn.demo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class AppTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testStringValue() {
        ValueOperations valueOps = redisTemplate.opsForValue();
        valueOps.set("first", "Hello World !!!");
        System.out.println("普通获取: " + valueOps.get("first"));
        valueOps.set("name", "ZhouHn", 1, TimeUnit.SECONDS);
        System.out.println("时效性立即获取: " + valueOps.get("name"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("时效性过时获取: " + valueOps.get("name"));
    }

    @Test
    public void testListValue() {
        ListOperations listOps = redisTemplate.opsForList();

    }

    @Test
    public void testHashValue() {
        HashOperations hashOps = redisTemplate.opsForHash();

    }


}
