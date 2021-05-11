package com.zhn.demo.somelib.aliyun.spring.api;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zhn.demo.somelib.aliyun.spring.InitializeConfig;
import com.zhn.demo.somelib.aliyun.spring.bean.AppKeyInfo;
import com.zhn.demo.somelib.aliyun.spring.bean.VmsConfig;

import java.util.Map;

public class Vms {

    private AppKeyInfo appKeyInfo = InitializeConfig.getAppKeyInfo();
    private VmsConfig vmsConfig = InitializeConfig.getVmsConfig();

    public JSONObject sendTextCallOnMan(String phoneNumber, Map<String, Object> paras) {
        String parasJson = JSONObject.toJSONString(paras);
        return sendTextCall(vmsConfig.getVmsWarnManTemplateId(), phoneNumber, parasJson);
    }

    public JSONObject sendTextCallOnWoman(String phoneNumber, Map<String, Object> paras) {
        String parasJson = JSONObject.toJSONString(paras);
        return sendTextCall(vmsConfig.getVmsWarnWomanTemplateId(), phoneNumber, parasJson);
    }

    private JSONObject sendTextCall(String templateCode, String phoneNumber, String param) {
        DefaultProfile profile = DefaultProfile.getProfile(appKeyInfo.getRegionId(), appKeyInfo.getAccessKeyId(), appKeyInfo.getAccessKeSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setDomain(vmsConfig.getVmsDomain());
        request.setMethod(MethodType.POST);
        request.setAction("SingleCallByTts");
        request.setVersion("2017-05-25");
        request.putQueryParameter("regionId", appKeyInfo.getRegionId());
        request.putQueryParameter("CalledShowNumber","");
        request.putQueryParameter("CalledNumber", phoneNumber);
        request.putQueryParameter("TtsCode", templateCode);
        request.putQueryParameter("TtsParam", param);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return JSONObject.parseObject(response.getData());
        } catch (ClientException e) {
            return (JSONObject) JSONObject.toJSON(e);
        }
    }

}
