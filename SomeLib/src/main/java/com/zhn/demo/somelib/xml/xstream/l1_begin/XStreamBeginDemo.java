package com.zhn.demo.somelib.xml.xstream.l1_begin;

import com.thoughtworks.xstream.XStream;

/**
 * @author ZhouHN
 * @desc 简单入门  包括Java对象xml 序列化与反序列化 操作
 * @date 16:45 2019/10/24 0024
 */
@SuppressWarnings("all")
public class XStreamBeginDemo {

    public static void main(String[] args) {

        People people = new People("ZhouHn", 24, "男");
        String toXml = toXml(people);
        System.out.println(toXml);

        people = toObj(toXml);
        System.out.println(people.toString());
    }

    /* 序列化 */
    static String toXml(Object obj) {
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);
    }

    /* 反序列化 */
    static People toObj(String xml) {
        return (People) xStream.fromXML(xml);
    }

    static XStream xStream = new XStream();

    static {
        // 避免 Security framework of XStream not initialized, XStream is probably vulnerable.提示
        XStream.setupDefaultSecurity(xStream);
        // 设置允许的类，避免com.thoughtworks.xstream.security.ForbiddenClassException 错误
        xStream.allowTypesByRegExp(new String[]{"com.zhn.demo.somelib.xml.xstream.*.*"});
    }

}

class People {
    private String name;
    private int age;
    private String sex;

    People(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
