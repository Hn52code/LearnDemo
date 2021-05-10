package com.zhn.demo.mode.singleton.type6;

public class Type6 {

    public static void main(String[] args) {

    }

}

// 单例模式 懒汉式1
class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
