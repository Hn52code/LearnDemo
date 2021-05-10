package com.zhn.pro.demo.wx.auth;

/**
 * @author ZhouHN
 * @desc 微信公众号（WeChat Official Accounts）Woa 配置实体类
 * @date 17:26 2019/10/25 0025
 */
public class WoaInfo {

    private final String appId;
    private final String appSecret;
    private final String token;
    private final String encodingAESKey;

    private String accessToken;
    private String jsApiAccessToken;

    public WoaInfo(String appId, String appSecret, String token, String encodingAESKey) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.token = token;
        this.encodingAESKey = encodingAESKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getToken() {
        return token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJsApiAccessToken() {
        return jsApiAccessToken;
    }

    public void setJsApiAccessToken(String jsApiAccessToken) {
        this.jsApiAccessToken = jsApiAccessToken;
    }

    @Override
    public String toString() {
        return "WoaInfo{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", token='" + token + '\'' +
                ", encodingAESKey='" + encodingAESKey + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", jsApiAccessToken='" + jsApiAccessToken + '\'' +
                '}';
    }

}
