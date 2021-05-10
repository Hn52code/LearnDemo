package com.zhn.demo.mode.factory.abs;


public class BJApplePizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("bj Apple Pizza");
        System.out.println("bj Apple Pizza 准备中");
    }
}
