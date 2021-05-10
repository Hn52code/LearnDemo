package com.zhn.demo.basic.current.bycountdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实体类：灯
 * 设定功能：打开 关闭/灯光，查看灯光状态
 */
public class Lamp {

    private boolean occupy = false;
    private boolean on_off = false;
    private CountDownLatch downLatch;

    public void callAndWaitOpen() {
        if (occupy) {
            System.out.println("唤醒线程：" + Thread.currentThread().getName() + " 有人去控制开关了！");
            return;
        }
        occupy = true;
        downLatch = new CountDownLatch(1);
        try {
            System.out.println("唤醒线程：" + Thread.currentThread().getName() + " 正在等待灯光打开。。。");
            for (int i = 0; i < 5; i++) {
                new Thread(this::open, Thread.currentThread().getName() + "====call-" + i).start();
            }
            downLatch.await(3, TimeUnit.SECONDS);
            state();
            downLatch = new CountDownLatch(1);
            occupy = false;
            on_off = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void open() {
        waitTime();
        if (occupy) {
            on_off = true;
            System.out.println("操作线程：" + Thread.currentThread().getName() + "开启灯光。。。");
            downLatch.countDown();
        } else {
            System.out.println("操作线程：" + Thread.currentThread().getName() + "还没调用callAndWaitOpen");
        }
    }

    private void waitTime() {
        try {
            int wait = (int) (System.currentTimeMillis() % 3799) + 200;
            System.out.println("当前线程" + Thread.currentThread().getName() + "等待:" + wait + "ms 唤醒线程操作");
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void state() {
        System.out.println("查询线程：" + Thread.currentThread().getName() + "==============灯光状态: " + (on_off ? "开启" : "关闭"));
    }

}
