package com.zhn.pro.web.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class DepartmentEntity {

    @Value("${department.name}")
    private String name;
    @Value("${department.desc}")
    private String desc;
    @Value("${department.number}")
    private String number;
    @Value("${department.age}")
    private String age;


    @Override
    public String toString() {
        return new StringJoiner(", ", DepartmentEntity.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("desc='" + desc + "'")
                .add("number='" + number + "'")
                .add("age='" + age + "'")
                .toString();
    }
}
