package com.zhn.demo.netty.netty2.server.msg.send;

/**
 *  发送消息扩展类：布撤防响应 
 * 1. 封装布撤防响应类
 * 2. 命令值：ComdType.OFFDEFENCE.getComValue();
 *
 * @author zhn 
 */
public class SendRespMsgDefence extends SendMsg {
	
//	public SendRespMsgDefence(StaType sta) {
//		// 长度等于 2+状态1+验证1 = 4
//		super(4, CmdType.DEFENCE.getComValue());
//		setSta(sta.getValue());
//		byte offDefenceEOI = completeEOI(CmdType.DEFENCE.getComValue(), sta.getValue(), null);
//		setEoi(offDefenceEOI);
//	}

}
