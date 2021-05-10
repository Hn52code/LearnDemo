package com.zhn.demo.mode.factory.method;

public class PizzaStore {

    private PizzaOrder pizzaOrder;

    public PizzaStore(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    public void order(String pizzaName) {
        this.pizzaOrder.makeit(pizzaName);
    }

}
