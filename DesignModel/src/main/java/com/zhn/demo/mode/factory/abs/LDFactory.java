package com.zhn.demo.mode.factory.abs;

public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String pizzaName) {
        if (pizzaName.equals("apple")) {
            return new LDApplePizza();
        } else if (pizzaName.equals("banana")) {
            return new LDBananaPizza();
        } else {
            throw new RuntimeException("没有该披萨");
        }
    }
}
