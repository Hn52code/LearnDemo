package com.zhn.demo.basic.thread.learn4.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable与Future:
 * 1. Runnable 封装一个异步允许的任务，可以想象成为一个没有参数的和返回值的方法，Callable和Runnable相似，但是有返回值。
 * Callable接口是一个参数化的类型，只有一个方法call() Callable
 * 2. Future类用来保存异步计算的结果，可以启动一个计算，将Future对象交给某个线程，然后忘掉它。
 * Future对象的所有者在结果计算好之后就可以获得它
 */

public class FutureTest1 {

    public static void main(String[] args) {
        // 1.封装异步任务的对象 callable
        Callable<Integer> callable = new IntegerCallable();
        // 2.保存异步任务计算结果的对象 future
        FutureTask<Integer> future = new FutureTask<>(callable);
        // 3.将future 对象交给一个线程执行。
        new Thread(future).start();
        try {
            // 4.future.get()获取结果，阻塞中，直到获得结果为止
            System.out.println("异步处理的结果：" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class IntegerCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return (int) (Math.random() * 1000);
    }
}