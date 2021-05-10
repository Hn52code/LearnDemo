package com.zhn.demo.netty.netty2.server.msg.emun;

/**
 * 报警：报警或探头类型，1个字节 <br>
 * 1. 以16进制  51 开始计数<br>
 *
 * @author zhn <br>
 */
public enum AlarmProbeType {

    NONE(0xFF, "无"),
    SMOKE_DETECTOR(0x03, "火灾"),
    TEMPERATURE(0x05, "温度"),
    ELECTRIC_LEAK(0x09, "电器火灾"),
    WATER_GAGE(0x0A, "水压"),
    GAS(0x0F, "燃气"),
    WIRED(0xEE, "有线"),
    MAIN_ELE(0x11, "主电"),
    SPARE_ELE(0x10, "备电"),
    WIRELESS_MODULE(0x12, "无线模块"),
    GSM_MODULE(0x13, "GSM模块"),
    ;

    private int id;
    private String type;

    AlarmProbeType(int id, String type) {
        this.id = (byte) id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static String getType(int value)  {
        for (AlarmProbeType alarmProbe : AlarmProbeType.values()) {
            if (value == alarmProbe.getId()) {
                return alarmProbe.getType();
            }
        }
        return "UnknownProbe";
    }

}
