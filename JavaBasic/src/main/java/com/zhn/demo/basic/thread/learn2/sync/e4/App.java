package com.zhn.demo.basic.thread.learn2.sync.e4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App {

    private static final ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            new Thread(() -> {
                System.out.println(dateFormat.get().format(new Date()));
            }).start();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().tryLock();
    Thread.currentThread().stop();

    }

}
