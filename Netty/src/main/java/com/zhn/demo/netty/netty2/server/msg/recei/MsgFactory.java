package com.zhn.demo.netty.netty2.server.msg.recei;

import com.zhn.demo.netty.netty2.server.msg.MessageException;
import com.zhn.demo.netty.netty2.server.msg.emun.CmdType;

public class MsgFactory {

    public static ReceiveMsg createReceiveMsg(CmdType type) throws MessageException {
        switch (type) {
            case PING:
                return new ReceiveRespMsgPing();
            case CTRL_PING:
                return new ReceiveRespMsgCtrlPing();
            case ALARM:
                return new ReceiveReqMsgAlarm();
            case LOGIN:
                return new ReceiveReqMsgLogin();
            case RESET:
                return new ReceiveRespMsgReset();
            case FAULT:
                return new ReceiveReqMsgFault();
            case CTRL_SWITCH:
                return new ReceiveRespMsgCtrlSwitch();
            default:
               throw new MessageException("命令不存在");
        }
    }

}
