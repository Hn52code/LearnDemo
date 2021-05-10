package com.zhn.demo.mode.singleton.type5;

public class Type5 {

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
            synchronized (Singleton.class){
                instance = new Singleton();
            }
        }
        return instance;
    }
}
