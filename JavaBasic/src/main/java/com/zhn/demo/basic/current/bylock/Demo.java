package com.zhn.demo.basic.current.bylock;

public class Demo {

    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.state();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                atm.callAndWaitOpen();
                atm.afterCall();
            }, "open-" + i).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        atm.state();
    }

}
