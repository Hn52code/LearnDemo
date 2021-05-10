package com.zhn.demo.mode.builder;

public abstract class ComputerBuilder {

    abstract void cpu();
    abstract void mainboard();
    abstract void memory();
    abstract Computer create();

    public Computer build(){
        this.cpu();
        this.memory();
        this.mainboard();
        return this.create();
    }

}
