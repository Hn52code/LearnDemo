package com.zhn.demo.mode.factory.simple;

public class PizzaOrder {

    private Pizza pizza;

    public void makeit(String pizzaName) {
        pizza = PizzaFactory.createPizza(pizzaName);
        pizza.prepare();
        pizza.make();
        pizza.box();
    }

}
