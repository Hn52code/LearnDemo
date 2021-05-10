package com.zhn.demo.redis.pubsub;

import redis.clients.jedis.Jedis;

public class RedisSubDemo {

    public static void main(String[] args) {
        Jedis jedis = RedisConfig.getJedis();
        jedis.subscribe(new RedisMsgListener(),"channel");
        System.out.println("继续执行后续代码。。。");
    }

}
