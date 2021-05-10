package com.zhn.demo.mode.singleton.type1;

public class Type1 {
    public static void main(String[] args) {

    }
}

// 单例模式 饿汉式1
class Singleton {

    private static Singleton instance = new Singleton();

    // 构造方法私有化
    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }


}
