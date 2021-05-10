package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户实体")
public class User {
    @ApiModelProperty(value = "id", name = "id", example = "1", required = true)
    private Integer id;
    @ApiModelProperty(value = "名称", name = "name", example = "张三", required = true)
    private String name;
    @ApiModelProperty(value = "性别", name = "sex", example = "男")
    private String sex;
    @ApiModelProperty(value = "年龄", name = "age", example = "18")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
