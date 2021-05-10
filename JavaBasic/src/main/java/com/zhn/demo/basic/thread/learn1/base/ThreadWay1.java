package com.zhn.demo.basic.thread.learn1.base;

/**
 * ※Java实现线程的方式一，通过集成Thread类 且重写 run方法来实现
 * 1. 创建
 * 2. 执行
 */
public class ThreadWay1 extends Thread {

    @Override
    public void run() {
        System.out.println("继承thread 子线程...");
    }

    public static void main(String[] args) throws Exception {
        ThreadWay1 threadWayOne = new ThreadWay1();
        threadWayOne.start();
        System.out.println("主线程...");
    }

}
