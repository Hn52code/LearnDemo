package com.zhn.pro.demo.wx.session.entity;

/**
 * @author ZhouHN
 * @desc 微信对话消息中，回复的视频消息
 * @date 16:55 2019/10/25 0025
 */
public class OutMsgVideo {

    private String MediaId;                 // 通过素材管理中的接口上传多媒体文件，得到的id
    private String Title;                   // 视频消息的标题
    private String Description;            // 视频消息的描述

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "OutMsgVideo{" +
                "MediaId='" + MediaId + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
