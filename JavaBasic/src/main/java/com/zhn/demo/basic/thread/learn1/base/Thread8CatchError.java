package com.zhn.demo.basic.thread.learn1.base;

/**
 * 前两个例子是创建和执行线程，除了知道创建和执行线程外，还需控制线程，以至于在多线程中合理完成相关业务。
 * 创建方式 实现runnable接口
 * 示例是 实现异常捕捉。
 */
public class Thread8CatchError implements Runnable {
    private ChildThreadCatchHandler catchHandler;

    public Thread8CatchError() {
        this.catchHandler = new ChildThreadCatchHandler();
    }

    public static class ChildThreadCatchHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("子线程异常：" + e.getMessage());
        }
    }


    @Override
    public void run() {
        // 设置异常处理
        Thread.currentThread().setUncaughtExceptionHandler(catchHandler);
        System.out.println("子线程...");
        // 抛出异常
        throw new RuntimeException("发生异常...");
    }

    /**
     * 在Java中，线程中的异常是不能抛出到调用该线程的外部方法中捕获的。
     * 因为每个线程是独立执行的代码片段,这些线程执行发生异常，应该由该线程自己处理（这个缘由）。
     * 因此在run方法外添加异常捕获或者外抛是不能的，只能在run方法内 try{}catch{}
     * 或者设置异常处理器，以及其他方式（在连接池中获取结果会附带异常）
     */

    public static void main(String[] args) {
        new Thread(new Thread8CatchError()).start();
    }

}
