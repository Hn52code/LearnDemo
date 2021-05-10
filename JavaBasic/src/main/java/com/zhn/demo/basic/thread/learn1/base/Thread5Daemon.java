package com.zhn.demo.basic.thread.learn1.base;

import java.util.Date;

/**
 * 前两个例子是创建和执行线程，除了知道创建和执行线程外，还需控制线程，以至于在多线程中合理完成相关业务。
 * 创建方式 实现runnable接口
 * 示例是守护线程
 */
public class Thread5Daemon implements Runnable {

    @Override
    public void run() {
        System.out.println("starting time " + new Date());
    }

    /**
     * 用于辅助用户线程，提供服务的，本质上和其他线程 一样的。
     * 当程序仅仅剩下守护线程了，虚拟机就退出，程序结束。
     * 应用场景：（1）来为其它线程提供服务支持的情况；
     * （2） 或者在任何情况下，程序结束时，这个线程必须正常且立刻关闭，就可以作为守护线程来使用；
     * 反之，如果一个正在执行某个操作的线程必须要正确地关闭掉否则就会出现不好的后果的话，那么这个线程就不能是守护线程，
     * 而是用户线程。通常都是些关键的事务，比方说，数据库录入或者更新，这些操作都是不能中断的。
     */
    public static void main(String[] args) {
        Thread5Daemon daemonThread = new Thread5Daemon();
        Thread thread = new Thread(daemonThread);
        thread.setDaemon(true); // 在启动前设置daemon为true。
        thread.start();
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println("scan resource...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
