package com.zhn.demo.spring.basic.aop;

import java.lang.reflect.Proxy;

/* 此处可用 jdk 动态代理，cglib动态代理 */
public class LoggingProxyOnJdk {

    private Object target;

    public LoggingProxyOnJdk(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开始调用");
                    Object result = method.invoke(target, args);
                    System.out.println("结果：" + result);
                    System.out.println("结束调用");
                    return result;
                });
    }

}
