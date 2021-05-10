package com.zhn.demo.spring.basic.ioc1;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @program: pads_sys
 * @description: 获取spring容器的bean工具类
 * @author: Mr.ZhouHN
 * @create: 2018-05-17 15:26
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        SpringContextUtil.context = context;
    }

    public static Object getBean(String name) {
        checkApplicationContext();
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        checkApplicationContext();
        return context.getBean(cls);
    }

    public static void cleanContext() {
        context = null;
    }

    private static void checkApplicationContext() {
        if (context == null) {
            throw new IllegalStateException("applicationContext未注入,请在spring.xml中定义SpringContextHolder");
        }
    }
}
