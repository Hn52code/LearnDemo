package com.zhn.pro.demo.wx.api;

import com.zhn.pro.demo.wx.api.entity.Button;
import com.zhn.pro.demo.wx.api.entity.MatchRule;
import com.zhn.pro.demo.wx.api.entity.Result;
import com.zhn.pro.demo.wx.auth.WoaInfo;
import com.zhn.pro.demo.wx.common.Constants;
import com.zhn.pro.demo.wx.common.utils.HttpUtil;
import com.zhn.pro.demo.wx.common.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhouHN
 * @desc 菜单管理，涉及普通自定义菜单和个性化菜单的增删改查
 * @date 17:17 2019/11/4 0004
 */
public class MenuManger {

    private WoaInfo woaInfo;

    public MenuManger(WoaInfo woaInfo) {
        this.woaInfo = woaInfo;
    }

    public Result create(Button button) {
        String para = JsonUtil.asJsonString(button);
        String url = Constants.MENU_CREATE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(json, Result.class);
    }

    public Map get() {
        String url = Constants.MENU_QUERY.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpGet(url, null, null);
        return JsonUtil.asJavaObject(json, Map.class);
    }

    public Result delete() {
        String url = Constants.MENU_DELETE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpDelete(url, null, null);
        return JsonUtil.asJavaObject(json, Result.class);
    }

    // 返回 {"menuid":"208379533"}
    public Map createPersonal(Button button, MatchRule rule) {
        Map<String, Object> map = new HashMap<>();
        map.put("button", button);
        map.put("matchrule", rule);
        String para = JsonUtil.asJsonString(map);
        String url = Constants.PERSONAL_MENU_CREATE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(json, Map.class);
    }

    // {"errcode":0,"errmsg":"ok"}
    public Result deletePersonal(String meunuid) {
        Map<String, Object> map = new HashMap<>();
        map.put("meunuid", meunuid);
        String url = Constants.PERSONAL_MENU_DELETE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpPostByJson(url, null, JsonUtil.asJsonString(map));
        return JsonUtil.asJavaObject(json, Result.class);
    }

    // 返回菜单列表
    public Map testPersonal(String openid) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", openid);
        String url = Constants.PERSONAL_MENU_TRY.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpPostByJson(url, null, JsonUtil.asJsonString(map));
        return JsonUtil.asJavaObject(json, Map.class);
    }


}
