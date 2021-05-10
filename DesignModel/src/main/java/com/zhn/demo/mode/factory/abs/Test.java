package com.zhn.demo.mode.factory.abs;


public class Test {
    public static void main(String[] args) {
        PizzaStore store = new PizzaStore(new BJFactory());
        store.order("apple");
        store.order("banana");

        store = new PizzaStore(new LDFactory());
        store.order("apple");
        store.order("banana");
    }
}
