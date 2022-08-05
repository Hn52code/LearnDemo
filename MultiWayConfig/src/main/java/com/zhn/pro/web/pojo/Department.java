package com.zhn.pro.web.pojo;

import java.util.StringJoiner;

public class Department {

    private Integer id;
    private String desc;
    private String name;
    private String number;
    private String age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("desc='" + desc + "'")
                .add("name='" + name + "'")
                .add("number='" + number + "'")
                .add("age='" + age + "'")
                .toString();
    }
}
