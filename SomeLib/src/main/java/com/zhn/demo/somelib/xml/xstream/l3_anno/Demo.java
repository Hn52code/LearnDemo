package com.zhn.demo.somelib.xml.xstream.l3_anno;

import com.thoughtworks.xstream.XStream;

public class Demo {

    public static void main(String[] args) {

        People people = new People();
        people.setAge(10);
        people.setName("zhouhain");
        XStream xStream = new XStream();
        // 设置扫描的类
        xStream.processAnnotations(People.class);
        // 自动检测注释
//        xStream.autodetectAnnotations(true);
        String xml = xStream.toXML(people);
        System.out.println(xml);

    }

}
