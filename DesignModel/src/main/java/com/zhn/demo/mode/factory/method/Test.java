package com.zhn.demo.mode.factory.method;


public class Test {
    public static void main(String[] args) {
        PizzaStore store = new PizzaStore(new BJPizzaOrder());
        store.order("apple");
        store.order("banana");


        store = new PizzaStore(new LDPizzaOrder());
        store.order("apple");
        store.order("banana");
    }
}
