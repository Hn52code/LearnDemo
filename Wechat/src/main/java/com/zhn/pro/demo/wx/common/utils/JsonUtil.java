package com.zhn.pro.demo.wx.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author ZhouHN
 * @desc JSON转化工具
 * @date 9:32 2019/10/26 0026
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String asJsonString(Object target) {
        try {
            return objectMapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T asJavaObject(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> List<E> asJavaListObject(String jsonStr, Class<E> clazz) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<List<E>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}