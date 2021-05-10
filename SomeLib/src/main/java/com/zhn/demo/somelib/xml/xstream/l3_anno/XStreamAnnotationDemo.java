package com.zhn.demo.somelib.xml.xstream.l3_anno;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XStreamAnnotationDemo {

    public static void main(String[] args) {
        List<String> course = new ArrayList<>();
        course.add("语文");
        course.add("数学");

        List<Relative> relatives = new ArrayList<>();
        relatives.add(new Relative("周某", "father"));
        relatives.add(new Relative("刘某", "mather"));

        Student student = new Student(true, "小周", 24, Calendar.getInstance().getTime(), course, relatives);

        XStream xStream = new XStream();
        // 设置扫描的类
        // xStream.processAnnotations(Student.class);
        // 自动检测注释
         xStream.autodetectAnnotations(true);
        System.out.println(xStream.toXML(student));

    }

}
