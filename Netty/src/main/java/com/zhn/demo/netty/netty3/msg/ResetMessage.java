package com.zhn.demo.netty.netty3.msg;

public class ResetMessage extends Message {

    /**
     * 状态值：成功，失败，超时，设备不存在
     * */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResetMessage{" +
                "status=" + status +
                "} " + super.toString();
    }
}
