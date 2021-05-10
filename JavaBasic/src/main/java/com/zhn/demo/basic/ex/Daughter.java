package com.zhn.demo.basic.ex;

public class Daughter {

    private String daughterName;

    public Daughter() {
        super();
        System.out.println(this);
    }

    public String getDaughterName() {
        return daughterName;
    }

    public void setDaughterName(String daughterName) {
        this.daughterName = daughterName;
    }

}
