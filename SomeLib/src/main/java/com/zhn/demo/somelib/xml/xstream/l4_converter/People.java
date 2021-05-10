package com.zhn.demo.somelib.xml.xstream.l4_converter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("people")
public class People {

    private String name;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                '}';
    }
}
