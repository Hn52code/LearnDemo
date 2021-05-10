package com.zhn.pro.demo.wx.session.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ZhouHN
 * @desc 微信对话消息中，第三方服务器接收消息的消息实体
 * @date 15:46 2019/10/25 0025
 */
@XStreamAlias("xml")
public class InputMessage extends Message {

    private String MsgId;               // 消息ID

    /* --------文本消息  MsgType : text ------------------------- */
    private String Content;             // 消息内容

    /* --------图片消息  MsgType : image ------------------------ */
    private String PicUrl;              // 图片地址
    private String MediaId;             // 媒体ID

    /* --------语音消息  MsgType : voice ------------------------ */
    private String Format;              // 语音格式
    private String Recognition;        // 语音文字

    /* --------视频消息  MsgType : shortvideo 和 video ----------- */
    private String ThumbMediaId;       // 视频ID

    /* --------地理位置消息  MsgType : location ------------------ */
    private String Label;               // 地理位置信息
    private Integer Scale;              // 地图放大倍数
    private Double Location_X;         // 地理位置维度
    private Double Location_Y;         // 地理位置经度

    /* --------链接消息  MsgType : link-------------------------- */
    private String Title;               // 消息标题
    private String Description;        // 消息描述
    private String Url;                 // 消息链接

    /* --------关注/取关事件  MsgType : even--------------------- */
    private String Event;               // subscribe关注 unsubscribe取关

    /* --------扫描二维码事件  MsgType : even-------------------- */
    // 未关注时扫描，Event：subscribe ; 关注时：Event:Scan
    private String EventKey;           // 事件KEY值，qrscene_为前缀，后面为二维码的参数值
    private String Ticket;             // 二维码的ticket，可用来换取二维码图片

    /* --------上班地理位置事件  MsgType : even-------------------- */
    // Event:LOCATION
    private Double Latitude;          // 地理位置纬度
    private Double Longitude;         // 地理位置经度
    private Double Precision;         // 地理位置精度

    /* --------自定义菜单事件  MsgType : even-------------------- */
    // Event:CLICK ; EventKey: 与自定义菜单接口中KEY值对应


    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Integer getScale() {
        return Scale;
    }

    public void setScale(Integer scale) {
        Scale = scale;
    }

    public Double getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(Double location_X) {
        Location_X = location_X;
    }

    public Double getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(Double location_Y) {
        Location_Y = location_Y;
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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getPrecision() {
        return Precision;
    }

    public void setPrecision(Double precision) {
        Precision = precision;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    @Override
    public String toString() {
        return "InputMessage{" +
                "MsgId='" + MsgId + '\'' +
                ", Content='" + Content + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                ", Format='" + Format + '\'' +
                ", Recognition='" + Recognition + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                ", Label='" + Label + '\'' +
                ", Scale=" + Scale +
                ", Location_X=" + Location_X +
                ", Location_Y=" + Location_Y +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Url='" + Url + '\'' +
                ", Event='" + Event + '\'' +
                ", EventKey='" + EventKey + '\'' +
                ", Ticket='" + Ticket + '\'' +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Precision=" + Precision +
                "} " + super.toString();
    }
}
