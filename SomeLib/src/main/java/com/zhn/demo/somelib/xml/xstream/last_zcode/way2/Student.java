package com.zhn.demo.somelib.xml.xstream.last_zcode.way2;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("student")
public class Student {

    private int stuId;  // 学生编号
    private int grade;  // 学生年级
    @XmlCData
    private String name; // 学生名称
    private String address; // 地址

    public Student() {
    }

    public Student(int stuId, int grade, String name, String address) {
        this.stuId = stuId;
        this.grade = grade;
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                '}';
    }

}
