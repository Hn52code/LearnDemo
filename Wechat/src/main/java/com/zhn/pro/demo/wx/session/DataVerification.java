package com.zhn.pro.demo.wx.session;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * @author ZhouHN
 * @desc 微信会话 数据校验
 * @date 9:25 2019/10/26 0026
 */
public class DataVerification {

    public static boolean verifyMsg(String signature, String timestamp, String nonce, String token) {
        String arr[] = {token, timestamp, nonce};
        Arrays.sort(arr);
        String sortStr = arr[0] + arr[1] + arr[2];
        String sha1Hex = DigestUtils.sha1Hex(sortStr);
        return sha1Hex.equals(signature);
    }

}
