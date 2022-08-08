package com.zhn.demo.spring.basic.ioc;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBootTest {

    public static void main(String[] args) {
        byXml();
        byAnn();
    }

    /**
     * 1.通过ApplicationContext获取到BeanFactory;
     * 2.通过BeanDefinitionBuilder构建BeanDefinition;
     * 3.调用beanFactory的registerBeanDefinition注入beanDefinition;
     * 4.使用ApplicationContext.getBean获取bean进行测试;
     */
    private static void byXml() {
        /* 实例 */
        Information information = new Information();
        information.setName("这是demo information");
        /* 上下文 */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
        /* 获取bean工厂 */
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        /* 注册实例 */
        beanFactory.registerSingleton("information", information);
        /* 调取实例 */
        System.out.println(context.getBean(Information.class).getName());
        System.out.println(SpringContextUtil.getBean(Information.class).getName());
    }

    /**
     * 注解类方式
     */
    private static void byAnn() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnConfigContext.class);
        context.registerShutdownHook();
        System.out.println(context.getBean(Information.class).getName());
    }

}

