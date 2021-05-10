package com.zhn.demo.basic.thread.learn1.base;

/**
 * 前两个例子是创建和执行线程
 * 本示例：控制线程(使得线程等待，使得电)，除了知道创建和执行线程外，还需控制线程，以至于在多线程中合理完成相关业务。
 * 创建方式 实现runnable接口
 * 本示例是线程优先级
 */
public class Thread7Priority implements Runnable {

    private String name;

    public Thread7Priority(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }

    // 设置线程是优先级，数值越大优先级越高。在不同的宿主机上，优先个数不一样，win 7个
    public static void main(String[] args) {
        Thread7Priority one = new Thread7Priority("One");
        Thread7Priority two = new Thread7Priority("Two");
        Thread t1 = new Thread(one);
        Thread t2 = new Thread(two);
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

}
