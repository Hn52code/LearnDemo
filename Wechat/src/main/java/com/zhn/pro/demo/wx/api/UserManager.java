package com.zhn.pro.demo.wx.api;

import com.zhn.pro.demo.wx.auth.WoaInfo;
import com.zhn.pro.demo.wx.common.Constants;
import com.zhn.pro.demo.wx.common.utils.HttpUtil;
import com.zhn.pro.demo.wx.common.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhouHN
 * @desc 用户管理，目前仅仅涉及用户列表，用户基本信息，用户备注
 * @date 17:20 2019/11/4 0004
 */
public class UserManager {

    private WoaInfo woaInfo;

    public UserManager(WoaInfo woaInfo) {
        this.woaInfo = woaInfo;
    }

    public Map getUserList(String next_openid) {
        String url = Constants.USER_LIST.replace("ACCESS_TOKEN", woaInfo.getAccessToken())
                .replace("NEXT_OPENID", next_openid);
        String jsonStr = HttpUtil.httpGet(url, null, null);
        return JsonUtil.asJavaObject(jsonStr, Map.class);
    }

    public Map getUserInfo(String openid) {
        String url = Constants.USER_INFO.replace("ACCESS_TOKEN", woaInfo.getAccessToken())
                .replace("OPENID", openid);
        String jsonStr = HttpUtil.httpGet(url, null, null);
        return JsonUtil.asJavaObject(jsonStr, Map.class);
    }

    public Map updateUserRemark(String openid, String remark) {
        Map<String, Object> map = new HashMap<>();
        map.put("openid", openid);
        map.put("remark", remark);
        String url = Constants.USER_REMARK.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpPostByJson(url, null, JsonUtil.asJsonString(map));
        return JsonUtil.asJavaObject(jsonStr, Map.class);
    }
}
