package com.zhn.demo.basic.current.bycountdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 设定灯光对象，由一个人打开，另一个人关闭
 */
public class Demo {

    public static void main(String[] args) {
        Lamp lamp = new Lamp();
//        for (int i = 0; i < 4; i++) {
//            new Thread(lamp::callAndWaitOpen, "son-" + i).start();
//        }
//        new Thread(lamp::open, "daughter").start();


        ExecutorService service = Executors.newFixedThreadPool(4);

        lamp.state();

//        service.submit(lamp::callAndWaitOpen);
//        service.submit(lamp::open);

        for (int i = 0; i < 1; i++) {
            try {
                service.submit(lamp::callAndWaitOpen);
//                service.submit(lamp::open);
                Thread.sleep((int) (System.currentTimeMillis() % 3499) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
