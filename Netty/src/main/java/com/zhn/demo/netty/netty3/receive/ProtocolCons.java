package com.zhn.demo.netty.netty3.receive;

/**
 * @description: 消息常用常量
 * @auther: Mr.ZhouHN
 * @Date: 2018/4/27 10:50
 */
public class ProtocolCons {

    // 起始符
    public final static int SOI = 0xA8;
    // 消息ID，设备MAC地址长度
    public final static int MAC_OFFSET = 16;
    // 全数据包最小的长度 1 + 2 + 16 + 2 + 1 + 1 +n(数据位)
    public final static int MIN_PACKAGE_LEN = 23;
    // 数据报中最大数据长度位16+2+1+1+n = 45
    public final static int MAX_DATA_LEN = 45;
    // 读取下标的最大长度，目前协议最大数据包长度 此处设定大于50
    public final static int MAX_INDEX = 50;
    // 读取注册时设备的网关长度
    public final static int DEVICE_GATEWAY_LEN = 20;

}
