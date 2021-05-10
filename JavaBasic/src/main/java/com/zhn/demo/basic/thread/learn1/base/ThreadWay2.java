package com.zhn.demo.basic.thread.learn1.base;

/**
 * ※Java实现线程的方式一，通过集成Thread类 且重写 run方法来实现
 * 1. 创建
 * 2. 执行
 */
public class ThreadWay2 implements Runnable {

    @Override
    public void run() {
        System.out.println("实现runnable 子线程...");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadWay2());
        thread.start();
        System.out.println("主线程...");
    }

}
