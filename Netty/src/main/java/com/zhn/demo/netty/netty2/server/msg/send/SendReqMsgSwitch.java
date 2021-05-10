package com.zhn.demo.netty.netty2.server.msg.send;

import com.zhn.demo.netty.netty2.server.msg.emun.CmdType;
import com.zhn.demo.netty.netty2.server.msg.emun.StaType;

/**
 * 发送消息扩展类：响应报警指令
 * 1. 封装响应报警指令
 * 2. ComdType.PING.getComValue();
 *
 * @author zhn
 */
public class SendReqMsgSwitch extends SendMsg {

    public SendReqMsgSwitch(int switchValue) {
        // 长度等于 命令2+状态1+验证1+消息1 = 5
        super(5, CmdType.CTRL_SWITCH.getComValue());
        // 设置状态为成功状态
        setSta(StaType.DEFAULT.getValue());
        byte[] info = {(byte) switchValue};
        setInfo(info);
        // 校验值计算
        byte pingEOI = completeEOI(CmdType.CTRL_SWITCH.getComValue(), StaType.DEFAULT.getValue(), info);
        setEoi(pingEOI);
    }

}
