package com.zhn.demo.netty.netty3.msg;

public class WarnAndBreakdownMessage extends Message {

    /* 类别 */
    private String type;
    /* 防区号 */
    private String areaNo;
    /* 探头类型 */
    private String probeType;
    /* 防区类型 */
    private String areaType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getProbeType() {
        return probeType;
    }

    public void setProbeType(String probeType) {
        this.probeType = probeType;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    @Override
    public String toString() {
        return "WarnAndBreakdownMessage{" +
                "type='" + type + '\'' +
                ", areaNo='" + areaNo + '\'' +
                ", probeType='" + probeType + '\'' +
                ", areaType='" + areaType + '\'' +
                "} " + super.toString();
    }
}
