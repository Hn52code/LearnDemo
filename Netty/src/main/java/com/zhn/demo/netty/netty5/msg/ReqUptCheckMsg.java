package com.zhn.demo.netty.netty5.msg;

public class ReqUptCheckMsg extends ReqMsgBody {

    private int alarmType;
    private int probeType;
    private int areaNo;
    private int areaType;

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public int getProbeType() {
        return probeType;
    }

    public void setProbeType(int probeType) {
        this.probeType = probeType;
    }

    public int getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(int areaNo) {
        this.areaNo = areaNo;
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }
}
