package com.zhn.pro.demo.wx.api.entity;

import java.util.Map;

public class Template {

    /**
     *    {
     *            "touser":"OPENID",
     *            "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
     *            "url":"http://weixin.qq.com/download",
     *            "miniprogram":{
     *              "appid":"xiaochengxuappid12345",
     *              "pagepath":"index?foo=bar"
     *            },
     *            "data":{
     *                    "first": {
     *                        "value":"恭喜你购买成功！",
     *                        "color":"#173177"
     *                    },
     *                    "keyword1":{
     *                        "value":"巧克力",
     *                        "color":"#173177"
     *                    },
     *                    "keyword2": {
     *                        "value":"39.8元",
     *                        "color":"#173177"
     *                    },
     *                    "keyword3": {
     *                        "value":"2014年9月22日",
     *                        "color":"#173177"
     *                    },
     *                    "remark":{
     *                        "value":"欢迎再次购买！",
     *                        "color":"#173177"
     *                    }
     *            }
     *        }
     */

    private String touser;                       // 接收者 openid
    private String template_id;                 // 模板id
    private String url;                          // 跳转url
    private Map<String, Object> miniprogram;     // 小程序，内部有参数 appid(小程序appid),pagepath(页面地址)
    private Map<String, Map<String, Object>> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Map<String, Object> miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, Map<String, Object>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Template{" +
                "touser='" + touser + '\'' +
                ", template_id='" + template_id + '\'' +
                ", url='" + url + '\'' +
                ", miniprogram=" + miniprogram +
                ", data=" + data +
                '}';
    }

}
