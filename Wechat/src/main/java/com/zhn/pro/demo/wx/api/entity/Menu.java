package com.zhn.pro.demo.wx.api.entity;

import java.util.List;

public class Menu {

/* view和click 的实例
   {
        "button":[
        {
            "type":"click",
                "name":"今日歌曲",
                "key":"V1001_TODAY_MUSIC"
        },
        {
            "name":"菜单",
                "sub_button":[
            {
                "type":"view",
                    "name":"搜索",
                    "url":"http://www.soso.com/"
            },
            {
                "type":"miniprogram",
                    "name":"wxa",
                    "url":"http://mp.weixin.qq.com",
                    "appid":"wx286b93c14bbf93aa",
                    "pagepath":"pages/lunar/index"
            },
            {
                "type":"click",
                    "name":"赞一下我们",
                    "key":"V1001_GOOD"
            }]
        }]
    }*/

    private String type;
    private String name;
    private String key;
    private String url;
    private String appid;
    private String pagepath;
    private List<Menu> sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<Menu> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Menu> sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", appid='" + appid + '\'' +
                ", pagepath='" + pagepath + '\'' +
                ", sub_button=" + sub_button +
                '}';
    }
}
