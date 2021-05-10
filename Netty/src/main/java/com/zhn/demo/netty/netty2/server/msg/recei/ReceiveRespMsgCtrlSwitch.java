package com.zhn.demo.netty.netty2.server.msg.recei;

// 包含阀门控制类型的设备的回复
public class ReceiveRespMsgCtrlSwitch extends ReceiveMsg {

    private int ctrlSwitch;

    public ReceiveRespMsgCtrlSwitch() {
    }

    @Override
    public void format() {
        ctrlSwitch = super.getMsgContent()[0];
    }

    public int getCtrlSwitch() {
        return ctrlSwitch;
    }

    public void setCtrlSwitch(int ctrlSwitch) {
        this.ctrlSwitch = ctrlSwitch;
    }

}
