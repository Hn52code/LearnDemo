package com.zhn.demo.somelib.xml.xstream.l3_anno;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("relative")
public class Relative {

    private String name; // 名称
    private String named;// 称呼

    public Relative(String name, String named) {
        this.name = name;
        this.named = named;
    }

    @Override
    public String toString() {
        return "Relative{" +
                "name='" + name + '\'' +
                ", named='" + named + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }
}
