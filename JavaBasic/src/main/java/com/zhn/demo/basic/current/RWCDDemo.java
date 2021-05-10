package com.zhn.demo.basic.current;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWCDDemo {

    public static void main(String[] args) throws InterruptedException {

//        new RWCDDemo().method();
        new RWCDDemo().method2();


    }

    // 方式1
    private void method() throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock writeLock = readWriteLock.writeLock();
        // 結果
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < 15; i++) {
            new Thread(() -> {

                if (writeLock.tryLock()) {
                    CountDownLatch latch = new CountDownLatch(1);
                    String main = "主线程" + Thread.currentThread().getName();
                    System.out.println(main + "：获取到锁");
                    try {
                        System.out.println(main + " 业务正在处理业务...");
                        // 模拟随机等待时间
                        try {
                            // 回调线程
                            new Thread(() -> {
                                String cb = main + "--业务线程" + Thread.currentThread().getName();
                                System.out.println(cb + " 业务处理回调中...");
                                // 等待时间固定
                                // int wait = 1000;
                                // 等待时间随机产生
                                int wait = (int) (Math.random() * 2 + 1) * 1000;
                                System.out.println(cb + " 处理业务所需时间：" + wait);
                                try {
                                    Thread.sleep(wait);
                                    map.put("result", "---- 這是結果 ----" + main);
                                    if (latch.getCount() > 0) {
                                        latch.countDown();
                                        System.out.println(cb + " 业务处理回调完成");
                                    } else {
                                        System.out.println(cb + " 业务处理回调结果已过期");
                                        map.remove("result");
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                            System.out.println(main + " 业务处理等待结果中...");
                            if (latch.await(1, TimeUnit.SECONDS)) {
                                System.out.println(main + " 业务处理状态检查：已完成");
                                // 打印結果
                                System.out.println(main + " " + map.get("result"));
                                return;
                            } else {
                                System.out.println(main + " 业务处理状态检查：未完成，已超时");
                                latch.countDown();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        map.remove("result");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        writeLock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "：没获取到锁");
                }

            }).start();
            Thread.sleep((int) (Math.random() * 4 + 1) * 1000);
        }
    }

    // 方式2
    private void method2() throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock writeLock = readWriteLock.writeLock();
        // 結果
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                invoke(writeLock, map);
            }).start();
            Thread.sleep((int) (Math.random() * 4 + 1) * 1000);
        }
    }

    private boolean invoke(Lock writeLock, Map<String, String> map) {
        if (writeLock.tryLock()) {
            CountDownLatch latch = new CountDownLatch(1);
            String main = "主线程" + Thread.currentThread().getName();
            System.out.println(main + "：获取到锁");
            try {
                System.out.println(main + " 业务正在处理业务...");
                // 模拟随机等待时间
                try {
                    // 回调线程
                    new Thread(() -> {
                        callback(main, map, latch);
                    }).start();
                    System.out.println(main + " 业务处理等待结果中...");
                    if (latch.await(1, TimeUnit.SECONDS)) {
                        System.out.println(main + " 业务处理状态检查：已完成");
                        // 打印結果
                        System.out.println(main + " " + map.get("result"));
                        return true;
                    } else {
                        System.out.println(main + " 业务处理状态检查：未完成，已超时");
                        latch.countDown();
                        return false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.remove("result");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "：没获取到锁");
        }
        return false;
    }

    private void callback(String main, Map<String, String> map, CountDownLatch latch) {
        String cb = main + "--业务线程" + Thread.currentThread().getName();
        System.out.println(cb + " 业务处理回调中...");
        // 等待时间固定
        // int wait = 1000;
        // 等待时间随机产生
        int wait = (int) (Math.random() * 2 + 1) * 1000;
        System.out.println(cb + " 处理业务所需时间：" + wait);
        try {
            Thread.sleep(wait);
            map.put("result", "---- 這是結果 ----" + main);
            if (latch.getCount() > 0) {
                latch.countDown();
                System.out.println(cb + " 业务处理回调完成");
            } else {
                System.out.println(cb + " 业务处理回调结果已过期");
                map.remove("result");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
