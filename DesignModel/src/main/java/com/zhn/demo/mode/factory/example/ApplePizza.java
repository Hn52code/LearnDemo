package com.zhn.demo.mode.factory.example;

public class ApplePizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("Apple Pizza");
        System.out.println("Apple Pizza 准备中");
    }
}
