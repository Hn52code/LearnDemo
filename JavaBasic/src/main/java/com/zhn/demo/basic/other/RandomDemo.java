package com.zhn.demo.basic.other;

import java.util.Random;

public class RandomDemo {

    public static void main(String[] args) {

        method3();

    }

    static void method1() {
        // 构造中带参数 则随机数执行的结果是一样的
        // 构造中不带参数 则随机数执行的结果是不一样的
        Random random = new Random(2);
        for (int i = 0; i < 5; i++) {
            System.out.println(random.nextInt(100));
        }
    }

    static void method2() {
        // Math.random() 返回值 [0.0,1.0）的double型数值，由于double类数的精度很高
        int max = 100; // 最大值
        int min = 1;   // 最小值
        int random = (int) (Math.random() * (max - min) + min);
        System.out.println(random);
    }

    static void method3() {
        // 借助系统随机数取余
        int max = 10000, min = 1;
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum % (max - min) + min);
        System.out.println(ran3);
    }

}
