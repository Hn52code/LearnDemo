package com.zhn.demo.netty.netty5.msg;

public class ReqUpInitMsg extends ReqMsgBody {

    private String type;
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
}
