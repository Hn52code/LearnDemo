package com.zhn.demo.somelib.xml.xstream.l4_converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamConverterDemo {

    public static void main(String[] args) {

        People people = new People("ZhouHaiNan");
        XStream xStream = new XStream(new DomDriver());
//        xStream.registerConverter(new XmlPeopleConverter());
        xStream.registerConverter(new XmlPeople2Converter());
        xStream.autodetectAnnotations(true);
        System.out.println(xStream.toXML(people));
    }

}
