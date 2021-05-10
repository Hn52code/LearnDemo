package com.zhn.demo.netty.netty2.server.msg.emun;

/**
 * 报警类型：1个字节<br>
 * 1. 以16进制01 01开始计数<br>
 *
 * @author zhn <br>
 */
public enum AlarmType {

    NONE(0xFF,"无"),
    HAND(0x00, "报警"),
    SOS(0x03, "SOS"),
    ANTI_DISMANTLE(0x04, "防拆"),
    MAIN_POWER(0x05,"主电断开"),
    TEST(0x06,"测试"),
    ;

    private byte id;
    private String type;

    AlarmType(int id, String type) {
        this.id = (byte) id;
        this.type = type;
    }

    public short getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static String getType(byte value) {
        for (AlarmType alarm : AlarmType.values()) {
            if (value == alarm.getId()) {
                return alarm.getType();
            }
        }
        return "UnknownAlarm";
    }

}
