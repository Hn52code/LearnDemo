package com.zhn.demo.netty.netty2.server.msg.send;

import com.zhn.demo.netty.netty2.server.msg.MsgConstant;

/**
 * 发送消息的基类
 * 1. 数据格式：起始符+长度符+命令符+状态符+验证符
 * 2. 其中起始符soi为固定值
 *
 * @author zhn
 */
public class SendMsg {

    // 起始符，初始化为固定值
    private int soi = MsgConstant.SOI;
    // 长度
    private short len;
    // 命令
    private short com;
    // 状态
    private byte sta;
    // 信息
    private byte[] info;
    // 验证
    private byte eoi;

    public SendMsg() {
        super();
    }

    public SendMsg(int len, short com) {
        super();
        this.len = (short) len;
        this.com = com;
    }

    /**
     * 说明： 计算验证位的值
     *
     * @param com  命令位
     * @param sta  状态位
     * @param info 信息位
     */
    protected byte completeEOI(int com, int sta, byte[] info) {
        byte infoValue = 0;
        if (info != null) {
            for (byte b : info) {
                infoValue += b;
            }
        }
        return (byte) ((com >> 8 & 0xff) + (com & 0xff) + sta + infoValue);
    }

    public int getSoi() {
        return soi;
    }

    public short getLen() {
        return len;
    }

    public short getCom() {
        return com;
    }

    public void setCom(short com) {
        this.com = com;
    }

    public byte getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = (byte) sta;
    }

    public byte getEoi() {
        return eoi;
    }

    public void setEoi(byte eoi) {
        this.eoi = eoi;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SendMsg [soi=" + soi + ", len=" + len + ", com=" + com + ", sta=" + sta + ", info=" + info + ", eoi="
                + eoi + "]";
    }

}
