package com.zhn.demo.netty.netty3.receive;

public enum IotDeviceModel {

    D_G_GT10(0x09, "GT10"),        //九小场所主机
    D_G_GT20(0x0a, "GT20"),        //九小场所主机
    D_G_PS808(0x11, "PS808"),
    D_G_PS808_2(0x0b, "PS808"),
    D_G_PS8010(0x14, "PS8010"),
    D_G_PS821(0x16, "PS821"),
    D_G_PSG8013(0x1a, "PSG8013"), //燃气机械手

    D_G_PSG805(0x12, "PSG805"), //第三方 燃气机械手
    D_G_PSG809(0x19, "PSG809"), //第三方 测试燃气机械手
    ;
    private byte key;
    private String value;

    IotDeviceModel(int key, String value) {
        this.key = (byte) key;
        this.value = value;
    }

    public byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getDeviceModel(int key) {
        for (IotDeviceModel model : IotDeviceModel.values()) {
            if (model.getKey() == key) {
                return model.getValue();
            }
        }
        return null;
    }

}
