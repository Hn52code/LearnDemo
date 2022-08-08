package com.zhn.demo.spring.basic.ioc;

public class Information {

    private String name;

    public Information() {
    }

    public Information(String name) {
        this.name = name;
        System.out.println("construct " + this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("setter " + this.name);
    }

    public void init() {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }

}
