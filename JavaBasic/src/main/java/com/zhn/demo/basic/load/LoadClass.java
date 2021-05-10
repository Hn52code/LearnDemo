package com.zhn.demo.basic.load;

public class LoadClass {

    public static void main(String[] args)throws ClassNotFoundException {
//      Class.forName("com.zhn.pro.B");
        new B();
    }


}

class A {

    static B b = new B();
    static C c = new C();

    static {
        System.out.println("加载 A...");
    }

    public A(Object o) {
        System.out.println("A 构成。。。" + o);
    }

    void say() {
        System.out.println("A HELLO。。。");
    }

}

class B {

    static A a = new A("B");

    public B(){
        System.out.println("B 构造");
    }

    static {
        System.out.println("加载 B...");
        a.say();
    }
}

class C {

    static {
        System.out.println("加载 C...");
    }

}
