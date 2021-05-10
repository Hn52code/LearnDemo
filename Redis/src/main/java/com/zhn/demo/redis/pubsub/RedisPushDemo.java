package com.zhn.demo.redis.pubsub;

import redis.clients.jedis.Jedis;

public class RedisPushDemo {

    public static void main(String[] args) {
        Jedis jedis = RedisConfig.getJedis();

     for(int i = 0;i<10;i++){
         jedis.publish("channel","1111111");
     }

    }

}
