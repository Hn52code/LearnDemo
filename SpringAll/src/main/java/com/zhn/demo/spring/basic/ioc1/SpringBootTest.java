package com.zhn.demo.spring.basic.ioc1;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBootTest {

    /**
     * 1.通过ApplicationContext获取到BeanFactory;
     * 2.通过BeanDefinitionBuilder构建BeanDefinition;
     * 3.调用beanFactory的registerBeanDefinition注入beanDefinition;
     * 4.使用ApplicationContext.getBean获取bean进行测试;
     */

    public static void main(String[] args) {
//        Config config = new Config();
//        config.setName("zhn");
//
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
//        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
//        beanFactory.registerSingleton("config", config);
//        System.out.println(context.getBean(Config.class).getName());

        ApplicationContext context;


    }

}

