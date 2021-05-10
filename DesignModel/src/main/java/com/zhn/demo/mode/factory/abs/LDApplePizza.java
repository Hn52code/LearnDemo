package com.zhn.demo.mode.factory.abs;


public class LDApplePizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("ld Apple Pizza");
        System.out.println("ld Apple Pizza 准备中");
    }
}
