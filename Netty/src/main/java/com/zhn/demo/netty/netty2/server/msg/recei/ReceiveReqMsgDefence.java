package com.zhn.demo.netty.netty2.server.msg.recei;

import com.zhn.demo.netty.netty2.server.msg.MessageException;
import com.zhn.demo.netty.netty2.server.msg.emun.DefenceType;

/**
 *  消息扩展类：布撤防响应类 
 * 1. 将消息当做布撤防响应类格式化消息
 *
 * @author zhn 
 */
public class ReceiveReqMsgDefence extends ReceiveMsg {

	private DefenceType type;

	@Override
	public void format()throws MessageException {
		byte value = getMsgContent()[0];
		type = DefenceType.getOrdinal(value);
	}

	public DefenceType getType() {
		return type;
	}

	public void setType(DefenceType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ReceiveRespMsgOffDefence [toString()=" + super.toString() + ", type=" + type + "]";
	}
	
}
