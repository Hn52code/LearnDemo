package com.zhn.demo.somelib.xml.xstream.l2_alias;

public class MenuDesc {

    private String descStr;
    private String createTime;

    public String getDescStr() {
        return descStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MenuDesc{" +
                "descStr='" + descStr + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
