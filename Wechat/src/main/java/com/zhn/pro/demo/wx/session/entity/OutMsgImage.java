package com.zhn.pro.demo.wx.session.entity;

/**
 * @author ZhouHN
 * @desc 微信对话消息中，回复消息的图片消息
 * @date 16:54 2019/10/25 0025
 */
public class OutMsgImage {

    private String MediaId;                 // 通过素材管理中的接口上传多媒体文件，得到的id。

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @Override
    public String toString() {
        return "OutMsgImage{" +
                "MediaId='" + MediaId + '\'' +
                '}';
    }

}
