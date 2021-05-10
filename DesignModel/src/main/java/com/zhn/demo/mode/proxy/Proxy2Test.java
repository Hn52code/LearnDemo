package com.zhn.demo.mode.proxy;


public class Proxy2Test {

    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDaoImpl();
        // 代理对象
        UserDao proxy = new JdkProxy(target).getProxyInstance();
        // 代理对象执行方法
        proxy.save();
    }

}
