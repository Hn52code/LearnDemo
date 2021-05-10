package com.zhn.demo.somelib.xml.xstream.last_zcode.way3;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("message")
public class Message {

    @XStreamConverter(XmlStringCDataConverter.class)
    private String desc;
    @XStreamConverter(XmlEnumCDataConverter.class)
    private Type type;

    public Message() {
    }

    public Message(String desc, Type type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                '}';
    }
}
