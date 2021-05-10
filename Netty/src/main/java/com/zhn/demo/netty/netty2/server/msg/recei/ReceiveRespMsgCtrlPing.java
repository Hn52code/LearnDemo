package com.zhn.demo.netty.netty2.server.msg.recei;

// 包含阀门控制类型的设备的心跳类
public class ReceiveRespMsgCtrlPing extends ReceiveMsg {

    private String cmdName;
    private int ctrlSwitch;

    public ReceiveRespMsgCtrlPing() {
    }

    @Override
    public void format() {
        cmdName = "阀门控制类设备心跳";
        ctrlSwitch = super.getMsgContent()[0];
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public int getCtrlSwitch() {
        return ctrlSwitch;
    }

    public void setCtrlSwitch(int ctrlSwitch) {
        this.ctrlSwitch = ctrlSwitch;
    }

}
