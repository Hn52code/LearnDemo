package com.zhn.demo.netty.netty2.server.msg.send;

import com.zhn.demo.netty.netty2.server.msg.emun.CmdType;
import com.zhn.demo.netty.netty2.server.msg.emun.StaType;

/**
 *  发送消息扩展类：响应报警指令
 * 1. 封装响应报警指令
 * 2. ComdType.PING.getComValue();
 *
 * @author zhn 
 */
public class SendRespMsgAlarm extends SendMsg {

	public SendRespMsgAlarm(StaType sta) {
		// 长度等于 命令2+状态1+验证1 = 4
		super(4, CmdType.ALARM.getComValue());
		// 设置状态为成功状态
		setSta(sta.getValue());
		// 校验值计算
		byte pingEOI = completeEOI(CmdType.ALARM.getComValue(), sta.getValue(), null);
		setEoi(pingEOI);

	}

}
