package com.zhn.pro.demo.wx.api;

import com.zhn.pro.demo.wx.api.entity.Result;
import com.zhn.pro.demo.wx.api.entity.Tag;
import com.zhn.pro.demo.wx.auth.WoaInfo;
import com.zhn.pro.demo.wx.common.Constants;
import com.zhn.pro.demo.wx.common.utils.HttpUtil;
import com.zhn.pro.demo.wx.common.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhouHN
 * @desc 标签管理，涉及标签的增删改查，以及和标签关联的用户增删查询操作
 * @date 17:18 2019/11/4 0004
 */
public class TagsManager {

    private WoaInfo woaInfo;

    public TagsManager(WoaInfo woaInfo) {
        this.woaInfo = woaInfo;
    }

    public Tag create(String name) {
        Tag tag = new Tag(name);
        String para = JsonUtil.asJsonString(tag);
        String url = Constants.TAGS_CREATE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String result = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(result, Tag.class);
    }

    public List<Tag> get() {
        String url = Constants.TAGS_GET.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpGet(url, null, null);
        return JsonUtil.asJavaListObject(jsonStr, Tag.class);
    }

    public Result update(Tag tag) {
        String para = JsonUtil.asJsonString(tag);
        String url = Constants.TAGS_UPDATE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(json, Result.class);
    }

    public Result delete(int id) {
        Tag tag = new Tag(id);
        String para = JsonUtil.asJsonString(tag);
        String url = Constants.TAGS_DELETE.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String json = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(json, Result.class);
    }

    // 参数 {   "tagid" : 134,   "next_openid":""//第一个拉取的OPENID，不填默认从头开始拉取 }
    public Map getTagUsers(int tagId, String next_openid) {
        Map<String, Object> map = new HashMap<>();
        map.put("tagId", tagId);
        map.put("next_openid", next_openid == null ? "" : next_openid);
        String url = Constants.TAGS_USER_GET.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpPostByJson(url, null, JsonUtil.asJsonString(map));
        return JsonUtil.asJavaObject(jsonStr, Map.class);
    }

    /*{
    "openid_list" : [//粉丝列表
    "ocYxcuAEy30bX0NXmGn4ypqx3tI0",
    "ocYxcuBt0mRugKZ7tGAHPnUaOW7Y"   ],
    "tagid" : 134
    } */
    // 为批量用户设置标签
    public Result userSetTag(int tagId, List<String> openids) {
        Map<String, Object> map = new HashMap<>();
        map.put("tagId", tagId);
        map.put("openid_list", openids);
        String para = JsonUtil.asJsonString(map);
        String url = Constants.USER_SET_TAG.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(jsonStr, Result.class);
    }


    // 为批量用户取消某标签
    public Result userUnsetTag(int tagId, List<String> openids) {
        Map<String, Object> map = new HashMap<>();
        map.put("tagId", tagId);
        map.put("openid_list", openids);
        String para = JsonUtil.asJsonString(map);
        String url = Constants.USER_UNSET_TAG.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpPostByJson(url, null, para);
        return JsonUtil.asJavaObject(jsonStr, Result.class);
    }

    // 获取用户的拥有的标签
    public List<Integer> getTagsOnUser(String openid) {
        Map<String, Object> map = new HashMap<>();
        map.put("openid", openid);
        String url = Constants.USER_TAGS_LIST.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpPostByJson(url, null, JsonUtil.asJsonString(map));
        return JsonUtil.asJavaListObject(jsonStr, Integer.class);
    }

}
