package com.zhn.demo.basic.current.bylock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实体类：灯
 * 设定功能：打开 关闭/灯光，查看灯光状态
 */
public class Lamp {

    private boolean on_off = false;
    private CountDownLatch downLatch;
    private Thread concurrentThread;
    private volatile boolean isOccupy = false;
    private volatile Lock lock = new ReentrantLock();

    // System.out.println("线程" + concurrentThread.getName() + "占用中");
    public void callAndWaitOpen() {
        if (this.lock.tryLock()) {
            try {
                concurrentThread = Thread.currentThread();
                downLatch = new CountDownLatch(1);
                System.out.println("当前线程：" + concurrentThread + " 执行唤醒线程，等待该线程处理");
                for (int i = 0; i < 1; i++) {
                    new Thread(this::open, concurrentThread + "callback-" + i).start();
                }
                downLatch.await(2000, TimeUnit.MICROSECONDS);
                System.out.println("当前线程：" + concurrentThread + " 结果回调-灯光状态: " + (on_off ? "开启" : "关闭"));

                System.out.println("当前线程：" + concurrentThread + " 灯光复位");
                this.on_off = false;
                System.out.println("当前线程：" + concurrentThread + " 执行多少次countdown" + downLatch.getCount());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                downLatch = new CountDownLatch(1);
                this.lock.unlock();
            }
        }else {
            System.out.println("线程" + concurrentThread.getName() + "占用中");
        }
    }

    public synchronized void open() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程：" + Thread.currentThread().getName() + " 执行打开开关");
        this.on_off = true;
        downLatch.countDown();
    }

    private void waitTime() {

    }


    private synchronized void occupy() {
        this.isOccupy = true;
    }

    private synchronized void unOccupy() {
        this.isOccupy = false;
    }

    public void state() {
        System.out.println("查询线程：" + Thread.currentThread().getName() + "==============灯光状态: " + (on_off ? "开启" : "关闭"));
    }

}
