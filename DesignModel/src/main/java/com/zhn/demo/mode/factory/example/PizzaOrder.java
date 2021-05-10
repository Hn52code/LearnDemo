package com.zhn.demo.mode.factory.example;

public class PizzaOrder {

    private Pizza pizza;

    public void makeit(String pizzaName) {
        if ("apple".equals(pizzaName)) {
            pizza = new ApplePizza();
        } else if ("banana".equals(pizzaName)) {
            pizza = new BananaPizza();
        } else {
            throw new RuntimeException("没有该披萨");
        }
        pizza.prepare();
        pizza.make();
        pizza.box();
    }

}
