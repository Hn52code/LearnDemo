package com.zhn.demo.netty.netty2.server.msg.send;

/**
 *  发送消息扩展类：远程布撤防请求指令 
 * 1. 封装远程布撤防指令
 * 2. ComdType.ONDEFENCE.getComValue();
 *
 * @author zhn 
 */
public class SendReqMsgRemoteDefence extends SendMsg {

    // 此处byte参数表示远程布撤防的类型，可查看defenceType枚举类的远程指令类型
//    public SendReqMsgRemoteDefence(int type) {
//        // 长度等于 命令2+状态1+验证1 = 5
//        super(5, CmdType.REMOTE_DEFENCE.getComValue());
//        setSta(StaType.DEFAULT.getValue());
//        byte[] info ={(byte) type};
//        setInfo(info);
//        byte onDefenceEOI = completeEOI(CmdType.REMOTE_DEFENCE.getComValue(), StaType.DEFAULT.getValue(), info);
//        setEoi(onDefenceEOI);
//    }

}
