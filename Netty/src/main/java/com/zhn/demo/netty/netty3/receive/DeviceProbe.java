package com.zhn.demo.netty.netty3.receive;

public enum DeviceProbe {
    /**
     * 烟感	03
     * 温度	05
     * 电气火灾	09
     * 水压	0A
     * 燃气	0F
     * 备电	10
     * 主电	11
     * 无线模块	12
     * GSM模块	13
     * 有线	EE
     */
    PROBE_SMOKE(0x03, "烟感"),
    PROBE_TEMP(0x05, "温度"),
    PROBE_ELE_LEAK(0x09, "电气火灾"),
    PROBE_WATER_GAGE(0x0A, "水压"),
    PROBE_GAS(0x0F, "燃气"),
    PROBE_RESERVE_ELE(0x10, "备电"),
    PROBE_MAIN_ELE(0x11, "主电"),
    ;
    private byte key;
    private String value;

    DeviceProbe(int key, String value) {
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
        for (DeviceProbe type : DeviceProbe.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        return null;
    }

}
