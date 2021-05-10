package com.zhn.demo.mode.singleton.type4;

public class Type4 {

    public static void main(String[] args) {

    }

}

// 单例模式 懒汉式1
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
