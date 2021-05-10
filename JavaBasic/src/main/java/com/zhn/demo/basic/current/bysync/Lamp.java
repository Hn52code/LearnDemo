package com.zhn.demo.basic.current.bysync;

/**
 * 实体类：灯
 * 设定功能：打开 关闭/灯光，查看灯光状态
 */
public class Lamp {

    private boolean on_off = false;

    public synchronized void open() {
        String name = Thread.currentThread().getName();
        System.out.print("当前线程：" + name + " ");
        if (this.on_off) {
            System.out.println("灯光已经打开，无需再次打开。。。");
        } else {
            this.on_off = true;
            System.out.println("开启灯光。。。");
        }
    }

    public synchronized void close() {
        String name = Thread.currentThread().getName();
        System.out.print("当前线程：" + name + " ");
        if (this.on_off) {
            this.on_off = false;
            System.out.println("关闭灯光。。。");
        } else {
            System.out.println("灯光已经关闭，无需再次关闭。。。");
        }

    }

    public void state() {
        System.out.println("灯光: " + (on_off ? " 开启" : "关闭"));
    }

}
