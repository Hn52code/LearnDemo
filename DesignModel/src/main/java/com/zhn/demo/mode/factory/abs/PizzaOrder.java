package com.zhn.demo.mode.factory.abs;

public class PizzaOrder {

    private Pizza pizza;
    private AbsFactory absFactory;

    public PizzaOrder(AbsFactory absFactory) {
        this.absFactory = absFactory;
    }

    public void makeit(String pizzaName) {
        pizza = absFactory.createPizza(pizzaName);
        pizza.prepare();
        pizza.make();
        pizza.box();

        System.out.println("====================");
    }


}
