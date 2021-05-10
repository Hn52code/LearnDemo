package com.zhn.demo.basic.thread.learn5.excutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService接口具有为预定执行（Scheduled Execution）和周期性重复执行而设计的方法
 */
public class ScheduledThreadPoolTest {


    public static void main(String[] args) {

        /* 预定线程池的工作流程 */
        // 1.创建线程池
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();

        // 2.调用schedule()传递runnable对象或者callable对象,延迟执行一次

//        pool.schedule(() -> {
//            System.out.println("预定线程池执行：延迟执行一次：初始化");
//        }, 1, TimeUnit.SECONDS);

        // 2.scheduleAtFixedRate()或者scheduleAtFixedRate() 传递runnable对象,周期性执行，类似定时器
        // 此处用来泛化Java中的定时器
        Runnable runnable = () -> System.out.println("预定线程池执行：定时，当前时间 " + new Date());
        pool.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);

    }

}
