package com.zhn.demo.basic.other;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 */
public class JiaoTiPrint {


    public static void main(String[] args) {
        FooBar fooBar = new FooBar(10,2);
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo ");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}


class FooBar {

    private boolean sx;
    private final Object lock = new Object();

    private CyclicBarrier barrier;

    private int n;

    public FooBar(int n,int count) {
        this.n = n;
        this.barrier = new CyclicBarrier(count);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (lock) {
            sx = true;
            lock.notifyAll();
            for (int i = 0; i < n; i++) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (lock) {
            while (!sx)
                lock.wait();
            for (int i = 0; i < n; i++) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}