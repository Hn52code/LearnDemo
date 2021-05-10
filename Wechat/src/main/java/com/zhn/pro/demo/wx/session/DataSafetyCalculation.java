package com.zhn.pro.demo.wx.session;

import com.zhn.pro.demo.wx.session.aes.AesException;
import com.zhn.pro.demo.wx.session.aes.WXBizMsgCrypt;

/**
 * @author ZhouHN
 * @desc 微信会话加解密工具
 * @date 9:23 2019/10/26 0026
 */
public class DataSafetyCalculation {

    public static String decryptMsg(
            String token,
            String encodingAesKey,
            String appId,
            String msgSignature,
            String timeStamp,
            String nonce,
            String postData) {
        String decryptMsg = "";
        try {
            WXBizMsgCrypt tool = new WXBizMsgCrypt(token, encodingAesKey, appId);
            decryptMsg = tool.decryptMsg(msgSignature, timeStamp, nonce, postData);
        } catch (AesException e) {
            e.printStackTrace();
        }
        return decryptMsg;
    }

    public static String ecryptMsg(String token,
                                   String encodingAesKey,
                                   String appId,
                                   String replyMsg,
                                   String timeStamp,
                                   String nonce) {
        String ecryptMsg = "";
        try {
            WXBizMsgCrypt tool = new WXBizMsgCrypt(token, encodingAesKey, appId);
            ecryptMsg = tool.encryptMsg(replyMsg, timeStamp, nonce);
        } catch (AesException e) {
            e.printStackTrace();
        }
        return ecryptMsg;
    }

}