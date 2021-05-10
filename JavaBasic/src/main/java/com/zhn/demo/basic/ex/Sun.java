package com.zhn.demo.basic.ex;

public class Sun extends Father {

    private String sunName;

    public Sun() {
        super();
        System.out.println(this);
    }

    public String getSunName() {
        return sunName;
    }

    public void setSunName(String sunName) {
        this.sunName = sunName;
    }
}
