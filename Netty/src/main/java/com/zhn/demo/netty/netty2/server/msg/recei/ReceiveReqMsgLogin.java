package com.zhn.demo.netty.netty2.server.msg.recei;

import com.zhn.demo.netty.netty2.server.msg.MessageException;
import com.zhn.demo.netty.netty2.server.msg.emun.EquipType;

/**
 * 消息扩展类：登录请求类
 * 1. 将接收的消息当做登录请求类处理消息，格式化内容
 *
 * @author zhn
 */
public class ReceiveReqMsgLogin extends ReceiveMsg {

    private String equipType;

    @Override
    public void format()throws MessageException {
        equipType = EquipType.getTypeByID(getMsgContent()[0]);
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    @Override
    public String toString() {
        return "ReceiveReqMsgLogin [toString()=" + super.toString() + ", equipType=" + equipType + "]";
    }

}
