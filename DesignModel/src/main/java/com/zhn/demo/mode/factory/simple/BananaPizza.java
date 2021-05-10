package com.zhn.demo.mode.factory.simple;


public class BananaPizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("Banana Pizza");
        System.out.println("Banana Pizza 准备中");
    }
}
