package com.zhn.demo.netty.netty2.server.msg.send;

import com.zhn.demo.netty.netty2.server.msg.emun.CmdType;
import com.zhn.demo.netty.netty2.server.msg.emun.StaType;

/**
 * 发送消息扩展类：心跳指令
 * 1. 封装成  请求或响应  心跳指令
 * 2. ComdType.PING.getComValue();
 *
 * @author zhn
 */
public class SendReqMsgPing extends SendMsg {

    public SendReqMsgPing() {
        // 长度等于 命令2+状态1+验证1 = 4
        super(4, CmdType.PING.getComValue());
        // 设置状态为成功状态
        setSta(StaType.DEFAULT.getValue());
        // 校验值计算
        byte pingEOI = completeEOI(CmdType.PING.getComValue(), StaType.DEFAULT.getValue(), null);
        setEoi(pingEOI);
    }

}
