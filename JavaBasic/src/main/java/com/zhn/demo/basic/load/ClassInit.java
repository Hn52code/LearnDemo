package com.zhn.demo.basic.load;

import javax.annotation.PostConstruct;

public class ClassInit {
    /*
    1.    父类静态代码块(包括静态初始化块，静态属性，但不包括静态方法)
    2.    子类静态代码块(包括静态初始化块，静态属性，但不包括静态方法 )
    3.    父类非静态代码块( 包括非静态初始化块，非静态属性 )
    4.    父类构造方法
    5.    子类非静态代码块 ( 包括非静态初始化块，非静态属性 )
    6.    子类构造方法
    7.    如果static块调用static变量，则必须将其放置在static变量下面
      */

    private static Object str;

    static {
        System.out.println(str);
        str = new Object();
        System.out.println("java static");
    }

    public ClassInit() {
        System.out.println("java construct");
    }

    public static Object getStr() {
        System.out.println("java method");
        return str;
    }

    public static void setStr(Object str) {
        ClassInit.str = str;
    }

    // spring 的最后加载，它必须建立在类已加载的情况下
    @PostConstruct
    public void postConstruct() {
        System.out.println("java postConstruct");
    }


}
