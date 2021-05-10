package com.zhn.demo.mode.prototype;

public class Info {
    private String distribute;

    public Info(String distribute) {
        this.distribute = distribute;
    }

    public String getDistribute() {
        return distribute;
    }

    public void setDistribute(String distribute) {
        this.distribute = distribute;
    }

    @Override
    public String toString() {
        return "Info{" +
                "distribute='" + distribute + '\'' +
                '}';
    }
}
