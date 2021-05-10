package com.zhn.demo.basic.thread.learn5.excutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorCompletionService（Scheduled Execution）和周期性重复执行而设计的方法
 */
public class CompletionThreadPoolTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /* 预定线程池的工作流程 */
        // 1.创建线程池
        ExecutorService service = Executors.newCachedThreadPool();

        // 2.封装相关任务组
        List<Callable<Integer>> tasks = new ArrayList<>();
        List<Future<Integer>> results = service.invokeAll(tasks);
        for (Future future : results) {
            future.get();
        }

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

        for (Callable<Integer> task : tasks) {
            service.submit(task);
        }

        completionService.take();


    }

}
