package com.zhn.demo.netty.netty2.server.msg.emun;

/**
 * 命令类型：心跳，登录，报警，布撤防,远程布撤防；2个字节 <br>
 * 1. 以16进制00 01开始计数<br>
 *
 * @author zhn <br>
 * @since 2018/10/11 控制类别的设备，有气感的机械手开关闸门
 */
public enum CmdType {

    UNKNOWN_CMD(0x00ff, "未知命令"),
    PING(0x0001, "心跳"),
    LOGIN(0x0002, "登录"),
    ALARM(0x0003, "报警"),
    //	DEFENCE(0x0004,"布撤防"),
//	REMOTE_DEFENCE(0x0005,"远程布撤防"),
    FAULT(0x0006, "故障"),
    RESET(0x0007, "复位"),
    CTRL_SWITCH(0x0008, "开关"),
    CTRL_PING(0x0009, "控制类开关设备心跳"),
    ;

    private short comValue;

    private String cmdName;

    CmdType(int value, String cmdName) {
        this.comValue = (short) value;
        this.cmdName = cmdName;
    }

    public short getComValue() {
        return comValue;
    }

    public String getCmdName() {
        return cmdName;
    }

    public static CmdType getCmdType(short value) {
        CmdType[] types = CmdType.values();
        for (CmdType cmdType : types) {
            if (cmdType.getComValue() == value) {
                return cmdType;
            }
        }
        return CmdType.UNKNOWN_CMD;
    }

}
