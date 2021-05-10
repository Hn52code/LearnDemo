package com.zhn.demo.mode.singleton.type7;

public class Type7 {

    public static void main(String[] args) {

    }

}

// 单例模式  静态内部类
class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }


    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
