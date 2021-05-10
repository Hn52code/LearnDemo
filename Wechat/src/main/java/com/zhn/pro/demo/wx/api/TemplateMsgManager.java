package com.zhn.pro.demo.wx.api;

import com.zhn.pro.demo.wx.api.entity.Result;
import com.zhn.pro.demo.wx.api.entity.Template;
import com.zhn.pro.demo.wx.auth.WoaInfo;
import com.zhn.pro.demo.wx.common.Constants;
import com.zhn.pro.demo.wx.common.utils.HttpUtil;
import com.zhn.pro.demo.wx.common.utils.JsonUtil;

/**
 * @author ZhouHN
 * @desc 模板消息管理，暂时仅仅涉及发送模板消息，不涉及模板增删改查。
 * @date 17:16 2019/11/4 0004
 */
public class TemplateMsgManager {

    private WoaInfo woaInfo;

    public TemplateMsgManager(WoaInfo woaInfo) {
        this.woaInfo = woaInfo;
    }

    public Result sendTemplateMsg(Template template) {
        String url = Constants.TEMPLATE_MSG_SEND.replace("ACCESS_TOKEN", woaInfo.getAccessToken());
        String jsonStr = HttpUtil.httpPostByJson(url, null, JsonUtil.asJsonString(template));
        return JsonUtil.asJavaObject(jsonStr, Result.class);
    }

}
