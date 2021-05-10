package com.zhn.demo.netty.netty3.receive;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * 数据包包含具体的数据信息，包含于报文
 */
public class ReceivingDatagram {

    private String mac;
    private ProtocolCmd cmd;
    private ProtocolStatus status;
    private ByteBuf data;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public ProtocolCmd getCmd() {
        return cmd;
    }

    public void setCmd(ProtocolCmd cmd) {
        this.cmd = cmd;
    }

    public ProtocolStatus getStatus() {
        return status;
    }

    public void setStatus(ProtocolStatus status) {
        this.status = status;
    }

    public ByteBuf getData() {
        return data;
    }

    public void setData(ByteBuf data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReceivingDatagram{" +
                "mac='" + mac + '\'' +
                ", cmd=" + cmd +
                ", status=" + status +
                ", data=" + ByteBufUtil.hexDump(data) +
                '}';
    }
}
