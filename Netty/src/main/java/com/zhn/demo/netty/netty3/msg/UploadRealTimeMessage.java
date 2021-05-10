package com.zhn.demo.netty.netty3.msg;

public class UploadRealTimeMessage extends Message {

    /* 开关 0关 1开 */
    private int on_off;

    public int getOn_off() {
        return on_off;
    }

    public void setOn_off(int on_off) {
        this.on_off = on_off;
    }


    @Override
    public String toString() {
        return "UploadRealTimeMessage{" +
                "on_off=" + on_off +
                "} " + super.toString();
    }
}
