package com.zhn.demo.basic.thread.learn1.base;

/**
 * 前两个例子是创建和执行线程，除了知道创建和执行线程外，还需控制线程，以至于在多线程中合理完成相关业务。
 * 创建方式 实现runnable接口
 * 示例是将子线程（线程句柄者）合并到主线程，线程句柄者调用join()方法，则使得主线停止执行，让该线程执行完后再执行主线程
 */
public class Thread3Join implements Runnable {

    @Override
    public void run() {
        System.out.println("子线程加入...");
        System.out.println("子线程执行...");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始...");
        Thread threadThree = new Thread(new Thread3Join());
        threadThree.start();
        // 指定线程加入主线程，其底层是main主线程等待，让该子线程执行。
        // 使得当前线程可以阻塞其他线程执行
        threadThree.join();
        System.out.println("主线程结束...");
    }

}
