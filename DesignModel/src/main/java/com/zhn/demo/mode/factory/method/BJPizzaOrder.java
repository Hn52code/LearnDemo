package com.zhn.demo.mode.factory.method;

public class BJPizzaOrder extends PizzaOrder {

    @Override
    protected Pizza createPizza(String pizzaName) {
        if (pizzaName.equals("apple")) {
            return new BJApplePizza();
        } else if (pizzaName.equals("banana")) {
            return new BJBananaPizza();
        } else {
            throw new RuntimeException("没有该披萨");
        }

    }
}
