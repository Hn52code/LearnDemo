package com.zhn.pro.demo.wx.session;

/**
 * @author ZhouHN
 * @desc 微信对话消息中的消息类型-枚举
 * @date 16:55 2019/10/28 0028
 */
public enum MessageType {

    /* -------文本消息----------- */
    text,               // 文本内容类型
    image,              // 图片内容类型
    voice,              // 语音消息类型
    video,              // 视频消息类型
    shortvideo,        // 短视频消息类型
    location,          // 地址消息类型
    link,               //  连接消息类型

    /* -------事件消息----------- */
    even,               // 事件消息类型
    LOCATION,          // 获取地理位置事件消息
    subscribe,         // 关注订阅消息
    unsubscribe,       // 取消订阅消息
    SCAN,               // 扫描事件消息
    CLICK,              // 点击事件消息
    VIEW,               // 网页事件消息

    ;

    public static boolean containType(String target) {
        for (MessageType value : MessageType.values()) {
            if (value.name().equals(target))
                return true;
        }
        return false;
    }

}
