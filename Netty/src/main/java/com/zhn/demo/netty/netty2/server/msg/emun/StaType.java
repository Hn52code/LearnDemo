package com.zhn.demo.netty.netty2.server.msg.emun;

import com.zhn.demo.netty.netty2.server.msg.MessageException;

/**
 * 状态值类型：成功，失败，默认,无效 <br>
 * 1. 以16进制  00 开始计数<br>
 *
 * @author zhn <br>
 */
public enum StaType {

    DEFAULT(0xFF),
    FAILURE(0x00),
    SUCCESS(0x01),
    IN_VAIN(0X02);

    private byte value;

    StaType(int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }

    public static StaType getOrdinal(int value) throws MessageException {
        StaType[] types = StaType.values();
        for (StaType staType : types) {
            if (staType.getValue() == value) {
                return staType;
            }
        }
        throw new MessageException("状态值不存在");
    }

}
