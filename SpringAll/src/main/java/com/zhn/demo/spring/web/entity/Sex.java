package com.zhn.demo.spring.web.entity;

public enum Sex {
    BOY(1,"boy"),
    GIRL(2,"girl"),
    ;
    private int value;
    private String desc;

    Sex(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
