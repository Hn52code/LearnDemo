package com.zhn.demo.mode.factory.method;


public abstract class Pizza {

    protected String pizzaName;

    abstract void prepare();

    protected void make() {
        System.out.println(pizzaName+ " 制作中");
    }

    protected void box() {
        System.out.println(pizzaName+ " 打包中");
    }

    protected void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }
}
