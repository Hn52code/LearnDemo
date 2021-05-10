package com.zhn.demo.redis.pubsub;

import redis.clients.jedis.Jedis;

public class RedisConfig {

    private static String HOST = "127.0.0.1";

    private static int PORT = 6379;

    private static String PASSWORD = "123456";

    private static int MAX_ACTIVE = 1024;

    private static int MAX_WAIT = 10000;

    private static int MAX_IDLE = 200;

    private static int TIMEOUT = 10000;

    private static boolean TEST_ON_BORROW = true;

    private static Jedis jedis;

    /* jedis 连接池*/
//    private static JedisPool jedisPool;

    static {
        /* 初始化单个jedis */
        jedis = new Jedis(HOST, PORT, TIMEOUT);
        jedis.auth(PASSWORD);

        /* jedis池化对象 */
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(MAX_IDLE);
//        config.setMaxTotal(MAX_ACTIVE);
//        config.setMaxWaitMillis(MAX_WAIT);
//        config.setTestOnBorrow(TEST_ON_BORROW);
//        jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD);
    }

    public static Jedis getJedis() {
        return jedis;
    }

//    public synchronized static Jedis getJedisFromPool() {
//        return jedisPool.getResource();
//    }

}
