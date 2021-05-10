package com.zhn.demo.netty.netty3.receive;

public enum DeviceWarn {

    /**
     * 警报	00
     * SOS	03
     * 防拆提示	04
     * 主电断开	05
     * 测试	06
     * 电压异常	07
     * 电流过高	08
     * 漏电	09
     * 温度过高	0A
     * */
    WARN_SIMPLE(0x00, "警报"),
    WARN_SOS(0x03, "SOS"),
    WARN_ANTI_DISMANTLE(0x04, "防拆提示"),
    WARN_MAIN_POWER(0x05, "主电断开"),
    // W_RESERVE_POWER(1, "备电断开"),
    WARN_TEST(0x06, "测试"),
    ;
    private byte key;
    private String value;

    DeviceWarn(int key, String value) {
        this.key = (byte) key;
        this.value = value;
    }

    public byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getWarnName(int key) {
        for (DeviceWarn type : DeviceWarn.values()) {
            if (type.getKey() == key) {
                return type.getValue();
            }
        }
        return null;
    }

}
