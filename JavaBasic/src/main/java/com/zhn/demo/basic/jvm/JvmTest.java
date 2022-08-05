package com.zhn.demo.basic.jvm;

public class JvmTest {

    public static void main(String[] args) {

        while (true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
