package com.zhn.demo.netty.netty3.msg;

public class ControlSwitchMessage extends ResetMessage {

    /* 打开关闭 */
    private int on_off;

    public int getOn_off() {
        return on_off;
    }

    public void setOn_off(int on_off) {
        this.on_off = on_off;
    }

    @Override
    public String toString() {
        return "ControlSwitchMessage{" +
                "on_off=" + on_off +
                "} " + super.toString();
    }
}
