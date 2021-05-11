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
import com.zhn.demo.somelib.aliyun.spring.bean.SmsConfig;

import java.util.List;
import java.util.Map;

public class Sms {

    private AppKeyInfo appKeyInfo = InitializeConfig.getAppKeyInfo();
    private SmsConfig smsConfig = InitializeConfig.getSmsConfig();

    public JSONObject sendSmsOnIDVerity(String param, String phoneNumber) {
        String jsonParam = "{\"code\":\"" + param + "\"}";
        return sendSms(smsConfig.getCommonSignName(), smsConfig.getVerityIdTemplateId(), jsonParam, phoneNumber);
    }

    public JSONObject sendSmsOnLogin(String param, String phoneNumber) {
        String jsonParam = "{\"code\":\"" + param + "\"}";
        return sendSms(smsConfig.getCommonSignName(), smsConfig.getDoLoginIdTemplateId(), jsonParam, phoneNumber);
    }

    public JSONObject sendSmsOnLoginErr(String param, String phoneNumber) {
        String jsonParam = "{\"code\":\"" + param + "\"}";
        return sendSms(smsConfig.getCommonSignName(), smsConfig.getLoginErrTemplateId(), jsonParam, phoneNumber);
    }

    public JSONObject sendSmsOnRegister(String param, String phoneNumber) {
        String jsonParam = "{\"code\":\"" + param + "\"}";
        return sendSms(smsConfig.getCommonSignName(), smsConfig.getRegisterTemplateId(), jsonParam, phoneNumber);
    }

    public JSONObject sendSmsOnPassword(String param, String phoneNumber) {
        String jsonParam = "{\"code\":\"" + param + "\"}";
        return sendSms(smsConfig.getCommonSignName(), smsConfig.getPasswordTemplateId(), jsonParam, phoneNumber);
    }

    public JSONObject sendSmsOnInfo(String param, String phoneNumber) {
        String jsonParam = "{\"code\":\"" + param + "\"}";
        return sendSms(smsConfig.getCommonSignName(), smsConfig.getInfoTemplateId(), jsonParam, phoneNumber);
    }

    public JSONObject sendSmsOnWarn(Map<String, Object> param, List<String> phoneNumbers) {
        return sendSms(smsConfig.getWarnSignName(), smsConfig.getWarnTemplateId(), JSONObject.toJSONString(param),
                String.join(",", phoneNumbers));
    }

    /* 同一签名下，可以使用下面的接口，一次对一个或多个好吗发送消息 */
    private JSONObject sendSms(String signName, String templateCode, String templateParam, String phoneNumbers) {
        DefaultProfile profile = DefaultProfile.getProfile(appKeyInfo.getRegionId(), appKeyInfo.getAccessKeyId(), appKeyInfo.getAccessKeSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setDomain(smsConfig.getSmsDomain());
        request.setMethod(MethodType.POST);
        request.setAction("SendSms");
        request.setVersion("2017-05-25");
        request.putQueryParameter("regionId", appKeyInfo.getRegionId());
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return JSONObject.parseObject(response.getData());
        } catch (ClientException e) {
            return (JSONObject) JSONObject.toJSON(e);
        }

    }

}
