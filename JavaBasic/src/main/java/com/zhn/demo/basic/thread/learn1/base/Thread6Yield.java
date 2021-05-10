package com.zhn.demo.basic.thread.learn1.base;

import java.util.Date;

/**
 * 前两个例子是创建和执行线程，除了知道创建和执行线程外，还需控制线程，以至于在多线程中合理完成相关业务。
 * 创建方式 实现runnable接口
 * 示例是线程让步，yield() 让步不一定有效果
 */
public class Thread6Yield implements Runnable {

    // Thread.yield()方法作用是：暂停当前正在执行的线程对象，并执行其他线程。
    // yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
    // 因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
    // 结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。

    @Override
    public void run() {
        System.out.println("thread：" + new Date());
    }

    public static void main(String[] args) {
        Thread6Yield yieldThread = new Thread6Yield();
        Thread t1 = new Thread(yieldThread);
        t1.start();
        Thread.yield();
        System.out.println("main：" + new Date());
    }
}
