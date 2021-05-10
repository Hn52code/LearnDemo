package com.zhn.demo.spring.web.restresult;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ApiResponseObject extends ObjectMapper {

    public ApiResponseObject() {
        // 排除值为空属性
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 进行缩进输出
        configure(SerializationFeature.INDENT_OUTPUT, true);
        // 将驼峰转为下划线
        setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

}
