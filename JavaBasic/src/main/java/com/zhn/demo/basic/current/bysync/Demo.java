package com.zhn.demo.basic.current.bysync;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        Lamp lamp = new Lamp();
        lamp.state();

        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(lamp::open, "open-"+i);
            Thread t2 = new Thread(lamp::close, "close-"+i);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }
        lamp.state();
    }

}
