package com.zhn.demo.rabbitmq.mq4javabean;

import java.util.StringJoiner;

public class Content {
    private String pub;

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Content.class.getSimpleName() + "[", "]")
                .add("pub='" + pub + "'")
                .toString();
    }
}
