package com.zhn.demo.baseweb.entity;

import java.util.Date;

public class Student {

    private Integer sno;
    private String sname;
    private Integer age;
    private Date birthday;
    private String sex;
    private String grade;

    public Student() {
    }

    public Student(String sname, Integer age) {
        this.sname = sname;
        this.age = age;
    }

    public Student(Integer sno, String sname, Integer age, Date birthday, String sex, String grade) {
        this.sno = sno;
        this.sname = sname;
        this.age = age;
        this.birthday = birthday;
        this.sex = sex;
        this.grade = grade;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
