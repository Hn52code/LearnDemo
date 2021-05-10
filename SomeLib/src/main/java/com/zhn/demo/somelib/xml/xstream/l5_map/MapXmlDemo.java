package com.zhn.demo.somelib.xml.xstream.l5_map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.HashMap;
import java.util.Map;

public class MapXmlDemo {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhn");
        map.put("age", 10);
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("map", Map.class);
        String xml = xStream.toXML(map);
        System.out.println(xml);
        new JettisonMappedXmlDriver();
//<map>
//  <entry>
//    <string>name</string>
//    <string>zhn</string>
//  </entry>
//  <entry>
//    <string>age</string>
//    <int>10</int>
//  </entry>
//</map>

    }
}
