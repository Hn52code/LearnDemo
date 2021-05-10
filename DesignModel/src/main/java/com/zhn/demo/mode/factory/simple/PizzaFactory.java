package com.zhn.demo.mode.factory.simple;

public class PizzaFactory {

    public static Pizza createPizza(String pizzaName){
        if ("apple".equals(pizzaName)) {
            return  new ApplePizza();
        } else if ("banana".equals(pizzaName)) {
            return new BananaPizza();
        } else {
            throw new RuntimeException("没有该披萨");
        }
    }

}
