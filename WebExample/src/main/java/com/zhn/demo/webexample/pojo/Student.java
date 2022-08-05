package com.zhn.demo.webexample.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Student {

    private int stuNo;
    private String stuName;
    private int age;
    private int sex;
    private String address;
    private Date birthday;

    private boolean isStudent; // mysql tinyint(1)表示 boolean
    private byte state;         // mysql tinyint默认4位，相当于 byte的取值范围

    private String createTime;
    private String loginTime;
    private Date createTime2;
    private Date loginTime2;


}
