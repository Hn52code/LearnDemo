package com.zhn.demo.mode.singleton.type3;

public class Type3 {

    public static void main(String[] args) {

    }

}

// 单例模式 懒汉式1
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
