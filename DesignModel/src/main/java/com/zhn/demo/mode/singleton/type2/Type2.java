package com.zhn.demo.mode.singleton.type2;

public class Type2 {

    public static void main(String[] args) {

    }

}

// 单例模式 饿汉式2
class Singleton {

    private static Singleton instance;

    static {
        instance = new Singleton();
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
