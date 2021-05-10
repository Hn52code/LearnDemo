package com.zhn.demo.basic.current;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock writeLock = readWriteLock.writeLock();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                if (writeLock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + "：获取到锁");
                    try {
                        Thread.sleep(5000);
                        System.out.println(Thread.currentThread().getName() + "：业务。。。");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        writeLock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "：没获取到锁");
                }
            }).start();
            Thread.sleep(3000);
        }

    }

}
