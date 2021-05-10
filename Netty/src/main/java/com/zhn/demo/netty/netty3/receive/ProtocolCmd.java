package com.zhn.demo.netty.netty3.receive;

public enum ProtocolCmd {

    CMD_PING(0x0001, "心跳命令"),
    // 可包含设备类型，设备网关。
    CMD_REGISTER(0x0002, "注册命令"),
    CMD_WARNING(0x0003, "警报命令"),
    CMD_BREAKDOWN(0x0006, "故障命令"),
    CMD_RESET(0x0007, "复位命令"),
    // 下面命令包括控制开关
    CMD_CTRL_SWITCH(0x0008, "控制阀门"),
    // 下面命令包括 上传开关状态。
    CMD_UPLOAD_REAL_TIME(0x0009, "上传实时数值"),
    CMD_UPLOAD_PARTS(0x000c, "上传主机配件信息"),

    CMD_ERROR(0xffff, "错误命令"),
    ;
    private short key;
    private String value;

    ProtocolCmd(int key, String value) {
        this.key = (short) key;
        this.value = value;
    }

    public short getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static ProtocolCmd getDataCmd(int key) {
        for (ProtocolCmd cmd : ProtocolCmd.values()) {
            if (cmd.getKey() == key) {
                return cmd;
            }
        }
        return null;
    }

}
