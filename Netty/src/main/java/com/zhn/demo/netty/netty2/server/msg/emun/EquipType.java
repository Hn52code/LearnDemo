package com.zhn.demo.netty.netty2.server.msg.emun;

/**
 * @description: 主机类型
 * @auther: Mr.ZhouHN
 * @Date: 2018/4/27 15:13
 */
public enum EquipType {

    GT10(0x09, "GT10"), //九小场所主机 GSM
    GT20(0x0A, "GT20"), //九小场所主机 GSM
    PS8010(0x14, "PS8010"), // GSM
    PS821(0x16, "PS821"), //  GSM
    PSG8013(0x1A, "PSG8013"), // 燃气机械手 GSM

    PSG805(0x12, "PSG805"), // 第三方设备，燃气机械手 GSM
    PSG809(0x19, "PSG809"), // 第三方测试设备，燃气机械手 GSM

    //    PS816(0x10, "PS816"), //NB烟感 NB
    //    PS808(0x11, "PS808"), //NB气感 NB
    //    PS819(0x13, "PS819"), // 机械手 NB
    //    PS8110(0x15, "PS8110"), // NB
    //    PSG806(0x17, "PSG806"), // NB
    //    PSG807(0x18, "PSG807"), // NB
    ;

    private byte id;
    private String type;

    EquipType(int id, String type) {
        this.id = (byte) id;
        this.type = type;
    }


    public byte getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getTypeByID(int id) {
        for (EquipType equip : EquipType.values()) {
            if (equip.getId() == id)
                return equip.type;
        }
        return "UnknownType";
    }

}
