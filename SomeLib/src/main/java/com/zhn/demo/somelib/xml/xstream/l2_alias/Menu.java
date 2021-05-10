package com.zhn.demo.somelib.xml.xstream.l2_alias;

import java.util.List;

public class Menu {

    private String key;
    private String name;
    private MenuDesc desc;
    private List<MenuItem> items;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public MenuDesc getDesc() {
        return desc;
    }

    public void setDesc(MenuDesc desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", desc=" + desc +
                ", items=" + items +
                '}';
    }
}
