package com.zhn.demo.mode.factory.method;


public abstract class PizzaOrder {

    private Pizza pizza;

    public void makeit(String pizzaName) {
        pizza = createPizza(pizzaName);
        pizza.prepare();
        pizza.make();
        pizza.box();

        System.out.println("====================");
    }

    protected abstract Pizza createPizza(String pizzaName);

}
