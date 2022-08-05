package com.zhn.demo.basic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean idle = true;

    public void getLock() {

        if (idle && lock.tryLock()) {
            idle = false;
            try {
                System.out.println("当前线程: " + Thread.currentThread() + " 获取锁");
//                    Thread.sleep(1500);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        lock.lock();
                        condition.signal();
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                condition.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                idle = true;
                lock.unlock();
            }
        } else {
            System.out.println("当前线程: " + Thread.currentThread() + " 未获取锁");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        final LockDemo lockDemo = new LockDemo();
        for (int i = 0; i < 8; i++) {
            new Thread(lockDemo::getLock).start();
            Thread.sleep(200);
        }

    }

}
