package com.zhn.demo.basic.thread.learn1.base;

import java.util.Date;

/**
 * 前两个例子是创建和执行线程
 * 本示例：控制线程(使得线程等待，使得电)，除了知道创建和执行线程外，还需控制线程，以至于在多线程中合理完成相关业务。
 * 创建方式 实现runnable接口
 * 本示例是终止线程
 */
public class Thread4Interrupt implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000); // 当前线程睡眠（此处指：Thread4Interrupt的实例）
                System.out.println("当前时间：" + new Date());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("终止时钟执行...");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始...");
        Thread thread = new Thread(new Thread4Interrupt());
        thread.start();
        Thread.sleep(5000);     // 当前线程睡眠（此处指：主线程）
        thread.interrupt();            // 线程的实例 调用线程中断方法执行打断线程。 一般不建议该方式终止线程。
        System.out.println("主线程结束...");
    }

}
