package com.zhn.demo.netty.netty2.server.msg.emun;

import com.zhn.demo.netty.netty2.server.msg.MessageException;

/**
 * @program: pads_sys
 * @description: 防区类型参数
 * @author: Mr.ZhouHN
 * @create: 2018-07-09 10:00
 */
public enum AreaType {

    NO_AREA(0xFF, "无"),
    GENERAL_AREA(0x00, "普通防区"),
    URGENT_AREA(0x01, "紧急防区"),
    STAY_AREA(0x02, "留守防区"),
    SMART_AREA(0x03, "智能防区"),
    CLOSE_AREA(0x04, "关闭防区"),
    DOOR_BELL_AREA(0x05, "门铃防区"),
    MEETING_AREA(0x06, "迎宾防区"),
    HELP_AREA(0x07, "求助防区"),
    ;

    private int id;
    private String value;

    AreaType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public static String getType(int id) throws MessageException {
        for (AreaType type : AreaType.values()) {
            if (type.getId() == id)
                return type.getValue();
        }
        throw new MessageException("防区类型不存在");
    }

}
