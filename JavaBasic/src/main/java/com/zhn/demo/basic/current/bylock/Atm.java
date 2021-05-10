package com.zhn.demo.basic.current.bylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实体类：Atm取钱业务
 * 设定要求：同一时间下，只能一个人取钱，其他人到该ATM时立即返回，取钱时，由具体的取钱机器具体操作，取钱机器同一下，只能有个一机器来操作
 */
public class Atm {

    private boolean on_off = false;
    private Thread concurrentThread;
    private Lock lock = new ReentrantLock();
    private volatile boolean isOccupy = false;
    private Condition condition = lock.newCondition();

    // System.out.println("线程" + concurrentThread.getName() + "占用中");
    public void callAndWaitOpen() {
        if (this.lock.tryLock()) {
            try {
                concurrentThread = Thread.currentThread();
                System.out.println("当前线程：" + concurrentThread + " 创建唤醒线程，等待该线程处理 开启占用");
                for (int i = 0; i < 2; i++) {
                    new Thread(this::open, concurrentThread + "callback-" + i).start();
                }
                occupy();
                if (!condition.await(2, TimeUnit.SECONDS)) {
                    System.out.println("当前线程：" + concurrentThread + " 唤醒线程执行超时。解除占用");
                    unOccupy();
                    return;
                }
                System.out.println("当前线程：" + concurrentThread + " 结果回调-灯光状态: " + (on_off ? "开启" : "关闭"));
                System.out.println("当前线程：" + concurrentThread + " 灯光复位");
                this.on_off = false;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.lock.unlock();
            }
        } else {
            System.out.println("线程" + concurrentThread.getName() + "占用中");
        }
    }

    public void afterCall() {
        // 为验证 调用callAndWaitOpen()，验证等待超时时是否会执行该方法
        System.out.println("当前线程：" + concurrentThread + " 调用 callAndWaitOpen 发生的事情");
    }

    public void open() {
        lock.lock();
        doOpen();
        condition.signal();
        System.out.println("当前线程：" + Thread.currentThread().getName() + " 执行打开开关");
        lock.unlock();
//        if (lock.tryLock()) {
//            doOpen();
//            condition.signal();
//            System.out.println("当前线程：" + Thread.currentThread().getName() + " 执行打开开关");
//            lock.unlock();
//        }else {
//            System.out.println("当前线程：" + Thread.currentThread().getName() + " 唤醒线程执行超时，未占用");
//        }
    }

    private void doOpen() {
        try {
            Thread.sleep(600);
            this.on_off = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void unOccupy() {
        this.isOccupy = false;
    }

    private synchronized void occupy() {
        this.isOccupy = true;
    }

    private synchronized boolean isOccupy() {
        return isOccupy;
    }

    public void state() {
        System.out.println("查询线程：" + Thread.currentThread().getName() + "======灯光状态: " + (on_off ? "开启" : "关闭"));
    }

}
