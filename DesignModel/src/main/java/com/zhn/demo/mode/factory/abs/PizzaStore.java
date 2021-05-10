package com.zhn.demo.mode.factory.abs;

public class PizzaStore {

    private PizzaOrder pizzaOrder;

    public PizzaStore(AbsFactory absFactory) {
        this.pizzaOrder = new PizzaOrder(absFactory);
    }

    public void order(String pizzaName) {
        this.pizzaOrder.makeit(pizzaName);
    }

}
