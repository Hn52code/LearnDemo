package com.zhn.demo.netty.netty3.receive;

public enum ProtocolStatus {

    STA_NONE(0xff),
    STA_FAILURE(0x00),
    STA_SUCCESS(0x01),
    STA_INVALID(0x02),
    ;
    private byte key;

    ProtocolStatus(int key) {
        this.key = (byte) key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(byte key) {
        this.key = key;
    }

    public static ProtocolStatus getOrdinal(int value) {
        for (ProtocolStatus staType : ProtocolStatus.values()) {
            if (staType.getKey() == value) {
                return staType;
            }
        }
        return null;
    }

}
