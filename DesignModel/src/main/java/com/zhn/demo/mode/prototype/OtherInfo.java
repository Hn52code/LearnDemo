package com.zhn.demo.mode.prototype;

import java.io.Serializable;

public class OtherInfo implements Cloneable, Serializable {

    private String school;

    public OtherInfo(String school) {
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "OtherInfo{" +
                "school='" + school + '\'' +
                '}';
    }

    @Override
    protected Object clone() {
        OtherInfo info = null;
        try {
            info = (OtherInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return info;
    }
}
