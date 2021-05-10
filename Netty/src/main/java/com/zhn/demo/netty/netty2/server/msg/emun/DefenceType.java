package com.zhn.demo.netty.netty2.server.msg.emun;

import com.zhn.demo.netty.netty2.server.msg.MessageException;

/**
 * 布防撤防类型：1个字节 <br>
 * 1. 以16进制一位 21 开始计数<br>
 *
 * @author zhn <br>
 */
public enum DefenceType {

//    KEY_OFF(CmdType.DEFENCE, 0, "按键撤防"),
//    KEY_ON(CmdType.DEFENCE, 1, "按键布防"),
//    KEY_STAY_ON(CmdType.DEFENCE, 2, "按键留守布防"),
//    REMOTE_OFF(CmdType.REMOTE_DEFENCE, 0, "远程撤防"),
//    REMOTE_ON(CmdType.REMOTE_DEFENCE, 1, "远程布防"),
//    REMOTE_STAY_OFF(CmdType.REMOTE_DEFENCE, 2, "远程留守布防");
//
    ;
    private CmdType com;
    private byte value;
    private String name;

    DefenceType(CmdType com, int value, String name) {
        this.com = com;
        this.value = (byte) value;
        this.name = name;
    }

    public CmdType getCom() {
        return com;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static DefenceType getOrdinal(byte value) throws MessageException {
        for (DefenceType type : DefenceType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new MessageException("防区类型不存在");
    }

}
