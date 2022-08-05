package com.zhn.pro.web.pojo;

import java.util.StringJoiner;

public class Employ {

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public Employ setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Employ setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employ.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("sex='" + sex + "'")
                .toString();
    }
}
