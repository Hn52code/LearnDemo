package com.zhn.demo.mode.factory.example;

public class BananaPizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("Banana Pizza");
        System.out.println("Banana Pizza 准备中");
    }
}
