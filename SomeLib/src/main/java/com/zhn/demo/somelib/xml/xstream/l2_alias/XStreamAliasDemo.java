package com.zhn.demo.somelib.xml.xstream.l2_alias;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

public class XStreamAliasDemo {

    public static void main(String[] args) {
        // 1.创建实体类对象
        Menu menu = new Menu();
        menu.setKey("123456");
        menu.setName("菜单");

        ArrayList<MenuItem> list = new ArrayList<>();
        MenuItem item = new MenuItem();
        item.setId("001");
        item.setName("菜单项");
        list.add(item);
        menu.setItems(list);
        MenuDesc desc = new MenuDesc();
        desc.setCreateTime("1234564623");
        desc.setDescStr("这是一个菜单。");
        menu.setDesc(desc);
        // 2.创建转换对象
        XStream xStream = new XStream();
        // 类名别名 ---- 指定类的别名
        xStream.alias("menu", Menu.class);
        // 字段别名 ---- 指定类的字段别名
        xStream.aliasField("menudesc", Menu.class, "desc");
        // 隐式集合 ---- 指定集合中的类型
        xStream.alias("item", MenuItem.class);

        // 3.转换-序列化：将实体对象转为 xmlStr
        String xmlStr = xStream.toXML(menu);
        System.out.println(xmlStr);

    }

}
