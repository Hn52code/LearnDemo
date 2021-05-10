package com.zhn.demo.basic.other;


/**
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 */
public class ShunXuPrint {

//    private static int count = 1;

    private boolean one;
    private boolean two;
    private final Object lock = new Object();


    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            one = true;
            lock.notifyAll();
        }

        // printFirst.run() outputs "first". Do not change or remove this line.
//        printFirst.run();
//        count++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock){
            while (!one)
                lock.wait();
            printSecond.run();
            two = true;
            lock.notifyAll();
        }

//        while (count != 2) ;
        // printSecond.run() outputs "second". Do not change or remove this line.
//        printSecond.run();
//        count++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock){
            while (!two)
                lock.wait();
            printThird.run();
        }

        //        while (count != 3) ;
        // printThird.run() outputs "third". Do not change or remove this line.
        //  printThird.run();
    }


    public static void main(String[] args) throws InterruptedException {

        ShunXuPrint print = new ShunXuPrint();
        new Thread(() -> {
            try {
                print.second(() -> System.out.println("two"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                print.third(() -> System.out.println("three"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                print.first(() -> System.out.println("one"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
