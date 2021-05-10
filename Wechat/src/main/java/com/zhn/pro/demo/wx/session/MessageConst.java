package com.zhn.pro.demo.wx.session;

/**
 * @author ZhouHN
 * @desc 微信对话消息，消息分类常量
 * @date 13:58 2019/10/26 0026
 */
@Deprecated
public class MessageConst {

    /*----------------对话消息------------------------*/
    public final static String MSG_TYPE_TEXT = "text";
    public final static String MSG_TYPE_IMAGE = "image";
    public final static String MSG_TYPE_VOICE = "voice";
    public final static String MSG_TYPE_VIDEO = "video";
    public final static String MSG_TYPE_LOCATION = "location";
    public final static String MSG_TYPE_LINK = "link";

    /*----------------事件消息-----------------------*/
    public final static String MSG_TYPE_EVEN = "even";
    public final static String EVEN_SUB = "subscribe";
    public final static String EVEN_UNSUB = "unsubscribe";
    public final static String EVEN_SCAN = "SCAN";
    public final static String EVEN_LOCATION = "LOCATION";
    public final static String EVEN_CLICK = "CLICK";
    public final static String EVEN_VIEW = "VIEW";


}
