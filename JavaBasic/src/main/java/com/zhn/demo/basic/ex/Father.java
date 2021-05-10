package com.zhn.demo.basic.ex;

public class Father {

    private String name;

    public Father() {
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
