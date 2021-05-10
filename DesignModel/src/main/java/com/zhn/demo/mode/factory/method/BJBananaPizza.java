package com.zhn.demo.mode.factory.method;


public class BJBananaPizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("bj Banana Pizza");
        System.out.println("bj Banana Pizza 准备中");
    }
}
