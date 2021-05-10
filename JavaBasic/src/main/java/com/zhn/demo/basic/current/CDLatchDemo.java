package com.zhn.demo.basic.current;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CDLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        // 結果
        Map<String, String> map = new HashMap<>();

        new Thread(() -> {
            try {

                System.out.println(Thread.currentThread().getName() + " 业务正在处理业务...");
                // 模拟随机等待时间
                int wait = 2;
                System.out.println(Thread.currentThread().getName() + " 等待唤醒时间：" + wait * 1000);
                if (latch.await(wait, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " 业务处理状态检查：已完成");
                } else {
                    System.out.println(Thread.currentThread().getName() + " 业务处理状态检查：未完成");
                    latch.countDown();
                }
                // 打印結果
                System.out.println(map.get("result"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 模拟 其他线程调用业务
        // Thread.sleep(4000);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 业务处理回调中...");
            // 等待时间固定
            // int wait = 8000;
            // 等待时间随机产生
            int wait = (int) (Math.random() * 4 + 1) * 1000;
            System.out.println(Thread.currentThread().getName() + " 处理业务所需时间：" + wait);
            try {
                Thread.sleep(wait);
                map.put("result", "---- 這是結果 ----" + Math.random());
                if (latch.getCount() > 0) {
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " 业务处理回调完成");
                } else {
                    System.out.println(Thread.currentThread().getName() + " 业务处理回调已超時");
                    map.remove("result");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
