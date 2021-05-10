package com.zhn.pro.demo.wx.session.entity;

/**
 * @author ZhouHN
 * @desc 微信对话消息实体基类
 * @date 16:45 2019/10/25 0025
 */
public class Message {

    private String ToUserName;          // 接收者
    private String FromUserName;        // 发送者
    private int CreateTime;             // 创建时间
    private String MsgType;              // 消息类型

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }
}
