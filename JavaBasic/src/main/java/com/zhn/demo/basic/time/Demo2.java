package com.zhn.demo.basic.time;

public class Demo2 {

    public static void main(String[] args) {

        Timer timer = new Timer();

        new Thread(()->{
            try {
                Thread.sleep(6000);
                timer.doBusiness2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (timer.isActive()) {
            try {
                Thread.sleep(2000);
                timer.doBusiness1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
