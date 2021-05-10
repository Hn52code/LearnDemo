package com.zhn.demo.basic.thread.learn5.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 执行器（Executor），线程池（ThreadPool）
 * 1.构建一个新的线程是有一定价值的，涉及到和操作系统的交互。如果程序中创建了大量的生命周期很短的线程，可以使用线程池
 * 一个线程池中包括许多准备运行的空闲线程，将runnable对象交给线程池，就会有一个线程调用runnable对象的run()方法，当run方法结束时，
 * 线程是不会死亡，它在pool中准备为下个请求提供服务。
 * 2.另外使用线程池是为了减少并发线程数量，固定线程池中的线程，可以大大降低性能开销。
 * 3.Executor中有许多静态工厂方法用来创建线程池。
 * 4.ExecutorService接口
 */
public class ThreadPoolTest1 {


    public static void main(String[] args) {

        /* 线程池的工作流程 */

        // 1.创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 2.调用submit提交Runnable或者Callable对象
        Runnable runnable = () -> System.out.println("线程池执行runnable...");
        pool.submit(runnable);
        // 不需要提交任务时，可关闭线程池
        pool.shutdown();

        // 可强转为线程池执行器，查看线程池信息
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) pool;
        System.out.println("线程池大小：" + threadPoolExecutor.getPoolSize());
        System.out.println("线程池信息：" + threadPoolExecutor.toString());
    }

}
