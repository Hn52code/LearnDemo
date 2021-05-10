package com.zhn.demo.netty.netty3.receive;

public enum DeviceArea {
    /*
    * -（无）FF
    * 普通	01
    * 紧急	02
    * 留守	03
    * 智能	04
    * 关闭	05
    * 门铃	06
    * 迎宾	07
    * 求助	08
    * 火警	09
    * 燃气	0A
    * 漏电	0B
    * 温度	0C
    * 水压	0D
    * */
    AREA_NONE(0xff, "-"),
    ;
    private byte key;
    private String value;

    DeviceArea(int key, String value) {
        this.key = (byte) key;
        this.value = value;
    }

    public byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getAreaName(int key) {
        for (DeviceArea type : DeviceArea.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        return null;
    }

}
