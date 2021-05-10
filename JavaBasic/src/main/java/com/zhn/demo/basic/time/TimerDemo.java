package com.zhn.demo.basic.time;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class TimerDemo {

    public static void main(String[] args) {

        // 设定活跃状态时，未活跃后5S，进入休眠，其中再次活跃则重新计时

        // 状态，有活跃和休眠中
        AtomicBoolean active = new AtomicBoolean(true);
        // 活跃时间戳
        AtomicLong activeTimestamp = new AtomicLong(System.currentTimeMillis());

        // 休眠线程
        new Thread(() -> {
            while (System.currentTimeMillis() - activeTimestamp.get() > 5000) {
                active.set(false);
            }
        }).start();

        // 激活线程
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                activeTimestamp.set(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 查看线程
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println("当前活跃状态: " + active.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }


}
