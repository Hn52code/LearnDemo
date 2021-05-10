package com.zhn.demo.basic.load;

public class ClassReferEachOther {

    public static void main(String[] args) {
        new A1(new A2());
    }
}

class A1 {
    private A2 a2;

    public A1(A2 a2) {
        this.a2 = a2;
    }

    public A2 getA2() {
        return a2;
    }
}

class A2 {
    private A1 a1;

    public void setA1(A1 a1) {
        this.a1 = a1;
    }

    public A1 getA1() {
        return a1;
    }
}