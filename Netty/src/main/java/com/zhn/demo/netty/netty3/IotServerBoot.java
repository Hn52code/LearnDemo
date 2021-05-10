package com.zhn.demo.netty.netty3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IotServerBoot {

    public static void main(String[] args) {

//        System.out.println(0xee & 0xff);
        new ClassPathXmlApplicationContext("spring.xml");
        new IotNettyServer().start(8001);
    }
}
