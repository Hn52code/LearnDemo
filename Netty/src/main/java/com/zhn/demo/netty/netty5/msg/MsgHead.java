package com.zhn.demo.netty.netty5.msg;

public class MsgHead {

    private int command; // 命令类型
    private int status;  // 状态
    private int msgType; // 消息类型：上报请求 0，上报回复 1，下发请求 2，下发回复 3

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
