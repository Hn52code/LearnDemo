package com.zhn.demo.spring.basic.ioc2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringBootTest {

    public static void main(String[] args) {
        ApplicationContext context;

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyContext.class);
        applicationContext.registerShutdownHook();


        ClassPathXmlApplicationContext context1;
        FileSystemXmlApplicationContext context2;


    }


}
