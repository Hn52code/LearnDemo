package com.zhn.demo.netty.netty3.msg;

public class RegisterMessage  extends Message{

    /* 设备类型 */
    private String type;
    /* 设备网关ICCID */
    private String gateway;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "RegisterMessage{" +
                "type='" + type + '\'' +
                ", gateway='" + gateway + '\'' +
                "} " + super.toString();
    }
}
