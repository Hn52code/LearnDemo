package com.zhn.demo.netty.netty3.reply;

public class RepliedPacket {

    private int length;
    private int cmd;
    private int sta;
    private int check;
    private byte[] data;

    protected void wrap() {
        int check = (this.getCmd() & 0xff) + (this.getCmd() >> 8 & 0xff) + this.getSta();
        if (this.getData() != null && this.getData().length > 0) {
            this.setLength(4 + this.getData().length);
            for (byte b : this.getData()) {
                check += b;
            }
        }else {
            this.setLength(4);
        }
        this.setCheck(check);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
