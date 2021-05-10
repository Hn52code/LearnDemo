package com.zhn.demo.netty.netty4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IotServerBoot {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("SpringNetty.xml");
    }
}
