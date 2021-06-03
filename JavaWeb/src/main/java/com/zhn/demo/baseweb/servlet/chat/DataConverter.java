package com.zhn.demo.baseweb.servlet.chat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getConverter() {
        return objectMapper;
    }
}
