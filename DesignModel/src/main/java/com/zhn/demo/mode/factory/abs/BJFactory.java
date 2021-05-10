package com.zhn.demo.mode.factory.abs;

public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String pizzaName) {
        if (pizzaName.equals("apple")) {
            return new BJApplePizza();
        } else if (pizzaName.equals("banana")) {
            return new BJBananaPizza();
        } else {
            throw new RuntimeException("没有该披萨");
        }
    }
}
