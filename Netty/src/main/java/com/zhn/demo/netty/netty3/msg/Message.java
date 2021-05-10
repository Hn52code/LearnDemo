package com.zhn.demo.netty.netty3.msg;

/* 本消息用于推送给第三方用户 */
public class Message {

    /* 时间戳 */
    private long date = System.currentTimeMillis();
    /* 设备模块id */
    private String mac;
    /* 上报消息类型 */
    private String cmd;

    public long getDate() {
        return date;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "Message{" +
                "date=" + date +
                ", mac='" + mac + '\'' +
                ", cmd='" + cmd + '\'' +
                '}';
    }
}
