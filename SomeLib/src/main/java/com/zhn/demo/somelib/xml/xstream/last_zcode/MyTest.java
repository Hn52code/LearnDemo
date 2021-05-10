package com.zhn.demo.somelib.xml.xstream.last_zcode;

import com.thoughtworks.xstream.XStream;
import com.zhn.demo.somelib.xml.xstream.last_zcode.way1.People;
import com.zhn.demo.somelib.xml.xstream.last_zcode.way2.Student;
import com.zhn.demo.somelib.xml.xstream.last_zcode.way2.XStreamFactory;
import com.zhn.demo.somelib.xml.xstream.last_zcode.way3.Message;
import com.zhn.demo.somelib.xml.xstream.last_zcode.way3.Type;
import org.apache.commons.lang.StringEscapeUtils;

public class MyTest {

    public static void main(String[] args) {

//        testWay1();
//        testWay2();
        testWay3();

    }


    static void testWay1() {
        // 1.创建需要转换的实体
        People people = new People(12, "zhou", "男");

        // 2.创建转化对象
        XStream xStream = new XStream();
        xStream.processAnnotations(People.class);

        // 3.调用转换函数返回结果
        String xmlStr = xStream.toXML(people);
        System.out.println(xmlStr);
//        <people>
//            <id>12</id>
//            <name>&lt;![CDATA[zhou]]&gt;</name>
//            <sex>男</sex>
//        </people>

        // 4.因为是cdata数据，所以有些字符不需要转义
        String result = StringEscapeUtils.unescapeXml(xmlStr);
        System.out.println(result);
//          <people>
//              <id>12</id>
//              <name><![CDATA[zhou]]></name>
//              <sex>男</sex>
//          </people>

    }

    static void testWay2() {

        Student student = new Student(130325, 2, "zhou", "shenzhen");
        XStream xStream2 = XStreamFactory.newInstance();
        xStream2.processAnnotations(Student.class);
        String xmlStr2 = xStream2.toXML(student);
        System.out.println(xmlStr2);

    }

    static void testWay3() {
        String str = "<![CDATA[image]]>";
        int length = str.length();
        str = str.substring(0, length - 3).substring(9);

        System.out.println(str);


        Message message = new Message("这是一个消息", Type.image);

        XStream xStream = new XStream();
        xStream.processAnnotations(Message.class);
        xStream.autodetectAnnotations(true);
        String toXML = xStream.toXML(message);

        System.out.println(toXML);

        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{Message.class});

        Message message2 = (Message) xStream.fromXML(toXML);

        System.out.println(message2.getType());
    }


}
