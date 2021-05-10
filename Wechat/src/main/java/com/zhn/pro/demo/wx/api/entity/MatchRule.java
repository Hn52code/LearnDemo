package com.zhn.pro.demo.wx.api.entity;

public class MatchRule {

    private String tag_id;                      // 用户标签的id
    private String sex;                         // 性别：男（1）女（2）
    private String country;
    private String province;
    private String city;
    private String client_platform_type;     // 客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)，
    private String language;                  // 语言信息，是用户在微信中设置的语言，具体请参考语言表： 1、简体中文 "zh_CN"

    @Override
    public String toString() {
        return "MatchRule{" +
                "tag_id='" + tag_id + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", client_platform_type='" + client_platform_type + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClient_platform_type() {
        return client_platform_type;
    }

    public void setClient_platform_type(String client_platform_type) {
        this.client_platform_type = client_platform_type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
