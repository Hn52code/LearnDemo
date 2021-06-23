package com.zhn.demo.rabbitmq.convert;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class ReplyMessageReceiver {

    public void onMessage(byte[] msgBytes) {
        System.out.print("byte[]-->");
        System.out.println(new String(msgBytes, Charset.forName("utf-8")));
    }

    /* */
//    public void onMessage(Map map) {
//        System.out.print("Map-->");
//        System.out.println(map.toString());
//    }

    /* */
//    public void onMessage(List list) {
//        System.out.print("list-->");
//        System.out.println(list.toString());
//    }


    public void onMessage(Person person) {
        System.out.print("Javabean-->");
        System.out.println(person);
    }

    public void onMessage(List<Person> list) {
        System.out.print("List-Javabean-->");
        System.out.println(list);
    }

    public void onMessage(Map<String, Person> map) {
        System.out.print("Map-Javabean-->");
        System.out.println(map);
    }

    public void onMessage(DataPackage dataPackage) {
        System.out.print("Javabean-content-map/list-->");
        System.out.println(dataPackage.toString());
    }

    public void onMessage(Data data) {
        System.out.print("Javabean-content-map/list-->");
        System.out.println(data.toString());
    }


}
