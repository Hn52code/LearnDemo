package com.zhn.demo.mode.factory.abs;


public class LDBananaPizza extends Pizza {

    @Override
    protected void prepare() {
        super.setPizzaName("ld Banana Pizza");
        System.out.println("ld Banana Pizza 准备中");
    }
}
