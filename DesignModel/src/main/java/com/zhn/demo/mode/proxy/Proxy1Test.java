package com.zhn.demo.mode.proxy;


public class Proxy1Test {

    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDaoImpl();
        // 代理对象
        StatusProxy proxy = new StatusProxy(target);
        // 代理对象执行方法
        proxy.save();
    }

}
