package com.zhn.demo.redis.cache.helloworld;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisSimpleTest {

    public static void main(String[] args) {
        // string(); // 字符串
        // map(); // map集合操作
        list(); //list集合
        // set(); //set集合
        // sort(); //排序
    }

    /**
     * 字符串操作
     * set：添加/或修改
     * del：删除
     * get：获取
     * m --> more 多个的意思
     * 支持单个/多个同时读写操作
     */
    public static void string() {
        Jedis jedis = RedisSimpleConfig.getJedis();

        /* 添加/修改 键值对 */
        jedis.set("name", "zhouhianan");
        System.out.println(jedis.get("name"));

        /* 追加 */
        jedis.append("name", ",age:18");
        System.out.println(jedis.get("name"));

        /* 删除 */
        jedis.del("name");
        System.out.println(jedis.get("name"));

        /* 设置多个键值对  格式为 key/value，*/
        jedis.mset("name", "张三", "sex", "男", "age", "10");
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("sex"));
        jedis.incr("age");
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("sex"));
        /* 同时获取多个！*/
        System.out.println(jedis.mget("name", "age", "sex"));

    }

    /**
     * map集合
     * h表示map
     * 其它的叠加string的
     */
    private static void map() {
        Jedis jedis = RedisSimpleConfig.getJedis();

        /* map集合 */
        Map<String, String> map = new HashMap<>();
        map.put("name", "lisi");
        map.put("age", "21");
        map.put("sex", "女");

        /* 添加/修改 map集合 */
        jedis.hmset("map", map);

        /* 读取 map*/
        //取出users中的Name,执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key,后面跟的是放入map中对象的key,后面的key可以是多个，是可变的
        List<String> hmget = jedis.hmget("map", "name", "age");
        System.out.println(hmget);

        /* 返回指定map 长度 */
        System.out.println(jedis.hlen("map"));
        /* 返回指定map 对应的所有 keys */
        System.out.println(jedis.hkeys("map"));
        /* 返回指定map 对应的所有 values */
        System.out.println(jedis.hvals("map"));

        /* 删除map中某个键值对 */
        jedis.hdel("map", "age");
        /* 删除map */
        jedis.del("map");

    }

    /**
     * redis操作List集合
     */
    public static void list() {
        Jedis jedis = RedisSimpleConfig.getJedis();
        // 开始前，先移除所有的内容
//        jedis.del("array");
//        System.out.println(jedis.lrange("array", 0, -1));
//
//        // 先向key java framework 中存放三条数据
//        // 左侧插入
//        jedis.lpush("array", "zhangsan");
//        jedis.lpush("array", "lisi");
//        jedis.lpush("array", "wangwu");
//
//        // 再取出所有数据jedis.lrange是按范围取出
//        // 第一个是key,第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
//        System.out.println(jedis.lrange("array", 0, -1));
//
//        // 删除
//        jedis.del("array");
//        System.out.println("删除");
//
//        // 右侧插入
//        jedis.rpush("array", "spring");
//        jedis.rpush("array", "struts");
//        jedis.rpush("array", "hibernate");
//
//        // 排序：左-右
//        System.out.println(jedis.lrange("array", 0, -1));

        jedis.lpush("list","one");

        jedis.blpop(5000,"list");

    }

    /**
     * redis操作set集合
     */
    public static void set() {
        Jedis jedis = RedisSimpleConfig.getJedis();
        // 添加
        jedis.sadd("user", "liuling");
        jedis.sadd("user", "xinxin");
        jedis.sadd("user", "ling");
        jedis.sadd("user", "zhangxinxin");
        jedis.sadd("user", "who");

        // 删除
        jedis.srem("user", "who");
        System.out.println(jedis.smembers("user"));//获取所有加入的value
        System.out.println(jedis.sismember("user", "who"));//判断who是否是user集合的元素
        System.out.println(jedis.srandmember("user"));
        System.out.println(jedis.scard("user"));//返回集合的元素个数
    }

    /**
     * redis排序
     */
    public static void sort() {
        Jedis jedis = RedisSimpleConfig.getJedis();
        // jedis 排序
        // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的)
        jedis.del("a");//先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println(jedis.lrange("a", 0, -1));
        System.out.println(jedis.sort("a"));//[1,3,6,9] //输入排序后结果
        System.out.println(jedis.lrange("a", 0, -1));
        jedis.expire("a",10);
    }

    public static void zset(){
        Jedis jedis = RedisSimpleConfig.getJedis();

    }

}
