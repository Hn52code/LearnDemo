package com.zhn.demo.basic.current.bysync;

public class Counter {

    public volatile int count = 0;

    public synchronized void inc() {
        count++;
    }

    public synchronized void dei() {
        count--;
    }

    public  int get(){
        return count;
    }

}
