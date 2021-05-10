package com.zhn.demo.netty.netty2.server.msg.emun;

import com.zhn.demo.netty.netty2.server.msg.MessageException;

public enum FaultType {

    FAULT(0x00, "故障"),
    LOSS(0x01, "丢失"),
    LOW_VOLTAGE(0x02, "低电压"),
    ;

    private int id;
    private String name;

    FaultType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getName(int id)throws MessageException {
        for (FaultType type : FaultType.values()) {
            if (type.getId() == id)
                return type.getName();
        }
        throw new MessageException("故障类型不存在");
    }

}
