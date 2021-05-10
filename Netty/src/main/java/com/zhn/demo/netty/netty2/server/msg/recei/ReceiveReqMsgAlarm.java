package com.zhn.demo.netty.netty2.server.msg.recei;

import com.zhn.demo.netty.netty2.server.DefenceNum;
import com.zhn.demo.netty.netty2.server.msg.MessageException;
import com.zhn.demo.netty.netty2.server.msg.emun.AlarmProbeType;
import com.zhn.demo.netty.netty2.server.msg.emun.AlarmType;
import com.zhn.demo.netty.netty2.server.msg.emun.AreaType;

/**
 * 消息扩展类：报警请求类
 * 1. 将消息当做报警请求类处理，格式化消息
 * 2. 其中需要处理消息是：2 + 2 + 1 个字节
 * 3. 转换为：报警类型+防区号+探头类型
 *
 * @author zhn
 */
public class ReceiveReqMsgAlarm extends ReceiveMsg {

    // 报警类型
    private String alarmType;
    // 防区号
    private String defenceNum;
    // 报警或探头类型
    private String probeType;
    // 防区类型
    private String areaType;

    public ReceiveReqMsgAlarm() {
        super();
    }

    @Override
    public void format()throws MessageException {
        byte[] b = super.getMsgContent();
        byte alarmValue = (byte) (b[0] & 0xff);
        alarmType = AlarmType.getType(alarmValue);
        int num = b[1] & 0xff;
        defenceNum = DefenceNum.format(num);
        probeType = AlarmProbeType.getType(b[2] & 0xff);
        areaType = AreaType.getType(b[3] & 0xff);
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getDefenceNum() {
        return defenceNum;
    }

    public void setDefenceNum(String defenceNum) {
        this.defenceNum = defenceNum;
    }

    public String getProbeType() {
        return probeType;
    }

    public void setProbeType(String probeType) {
        this.probeType = probeType;
    }

    @Override
    public String toString() {
        return "ReceiveReqMsgAlarm [toString()=" + super.toString() + ", alarmType=" + alarmType + ", defenceNum="
                + defenceNum + ", probeType=" + probeType + ",areaType=" + areaType + "]";
    }

}
