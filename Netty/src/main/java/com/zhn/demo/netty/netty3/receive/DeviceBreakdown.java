package com.zhn.demo.netty.netty3.receive;

public enum DeviceBreakdown {

    BREAKDOWN_SIMPLE(0x00, "故障"),
    BREAKDOWN_LOSE(0x01, "丢失"),
    BREAKDOWN_LOW_POWER(0x02, "低电压"),
    ;
    private byte key;
    private String value;

    DeviceBreakdown(int key, String value) {
        this.key = (byte) key;
        this.value = value;
    }

    public byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getBreakdownName(int key) {
        for (DeviceBreakdown type : DeviceBreakdown.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        return null;
    }

}
