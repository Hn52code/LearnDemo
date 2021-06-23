package com.zhn.demo.rabbitmq.convert;

import java.util.StringJoiner;

public class ContentImpl1 extends Content {

    private String gateWay;

    public String getGateWay() {
        return gateWay;
    }

    public void setGateWay(String gateWay) {
        this.gateWay = gateWay;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContentImpl1.class.getSimpleName() + "[", "]")
                .add("gateWay='" + gateWay + "'")
                .add("super='" + super.toString() + "'")
                .toString();
    }
}
