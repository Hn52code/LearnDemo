package com.zhn.demo.webexample.pojo;

import com.zhn.demo.webexample.util.ExcelVOAttribute;

public class People {

    @ExcelVOAttribute(name = "编号",column = "A")
    private int id;
    @ExcelVOAttribute(name = "名称",column = "B")
    private String name;
    @ExcelVOAttribute(name = "性别",column = "C")
    private String sex;
    @ExcelVOAttribute(name = "年龄",column = "D")
    private int age;
    @ExcelVOAttribute(name = "地址",column = "E")
    private String addr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
