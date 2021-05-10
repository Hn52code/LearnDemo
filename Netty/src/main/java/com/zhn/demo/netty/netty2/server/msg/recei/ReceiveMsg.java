package com.zhn.demo.netty.netty2.server.msg.recei;

import com.zhn.demo.netty.netty2.server.msg.MessageException;

import java.util.Arrays;

/**
 * 接收消息的基类<br>
 * 1. 包括公共字段：发送ID,命令类型,状态 ,消息内容 <br>
 *
 * @author zhn <br>
 */
public class ReceiveMsg implements ReceiveMsgFormat {

    // 设备MAC地址，或者发送ID
    private String sendID;
    // 消息命令类型
    private short msgComValue;
    // 消息状态，成功失败，补位
    private byte msgSta;
    // 消息内容
    private byte[] msgContent;

    @Override
    public void format() throws MessageException { }

    public ReceiveMsg() {
        super();
    }

    public ReceiveMsg(String sendID, short msgComValue, byte msgSta, byte[] msgContent) {
        this();
        this.sendID = sendID;
        this.msgComValue = msgComValue;
        this.msgSta = msgSta;
        this.msgContent = msgContent;
    }

    public short getMsgCom() {
        return msgComValue;
    }

    public void setMsgComValue(short msgComValue) {
        this.msgComValue = msgComValue;
    }

    public byte[] getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }

    public String getSendID() {
        return sendID;
    }

    public void setSendID(String sendID) {
        this.sendID = sendID;
    }

    public byte getMsgSta() {
        return msgSta;
    }

    public void setMsgSta(byte msgSta) {
        this.msgSta = msgSta;
    }

    @Override
    public String toString() {
        return "ReceiveMsg [msgComValue=" + msgComValue + ", sendID=" + sendID + ", msgContent=" + Arrays.toString(msgContent)
                + "]";
    }

}
