package com.zhn.demo.rabbitmq.channel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Jackson Jar包 转换工具类
 */
public class JacksonUtil {

    /* 转换对象 */
    private static ObjectMapper mapper = null;

    /* 初始化赋值 */
    static {
        mapper = new ObjectMapper();
    }

    /* 将 json字符串转为指定Javabean对象 */
    public static <T> T asJavaObject(String jsonStr, Class<T> clazz) {
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 将Object对象转为json字符串 */
    public static String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 将Java map 对象转为指定Javabean对象*/
    public static <T> T mapToJavaObject(Map<String, Object> map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }


}
