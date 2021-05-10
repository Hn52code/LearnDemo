package com.zhn.demo.somelib.xml.xstream.l3_anno;

import com.thoughtworks.xstream.annotations.*;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
@XStreamAlias("student")
public class Student {

    @XStreamAsAttribute // 将该字段 变设置为属性
    private boolean sign;

    // 使用库中带的布尔转换器
    @XStreamConverter(value = BooleanConverter.class, booleans = {false}, strings = {"yes", "no"})
    private boolean important;

    private String name; // 名称

    private int age;    // 年龄

    @XStreamConverter(XmlDateFormatConverter.class)  // 自定义日期转换器
    private Date birthday; // 生日

    // 隐藏集合，这是子元素
    @XStreamImplicit(itemFieldName = "course") // 使得下面字段，仅仅展现子元素，且可设置树标签名称
    private List<String> courses;  // 课程

    private List<Relative> relatives;

    @XStreamOmitField // 忽略此字段
    private String unless;

    public Student(boolean sign,
                   String name,
                   int age,
                   Date birthday,
                   List<String> courses,
                   List<Relative> relatives) {
        this.sign = sign;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.courses = courses;
        this.relatives = relatives;
    }

    public String getUnless() {
        return unless;
    }

    public void setUnless(String unless) {
        this.unless = unless;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sign=" + sign +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", courses=" + courses +
                ", relatives=" + relatives +
                '}';
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }


}
