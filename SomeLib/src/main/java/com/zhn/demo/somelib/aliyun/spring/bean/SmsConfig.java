package com.zhn.demo.somelib.aliyun.spring.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;

public class SmsConfig {

    private String smsDomain;
    private Map<String, String> signMap = new HashMap<>();
    private Map<String, String> templateMap = new HashMap<>();

    public SmsConfig(Properties properties) {
        this.smsDomain = properties.getProperty("ali.sms.domain");
        this.signMap.put("common", properties.getProperty("ali.sms.sign.common"));
        this.signMap.put("warn", properties.getProperty("ali.sms.sign.warn"));
        this.templateMap.put("verityId", properties.getProperty("ali.sms.templat.verityID"));
        this.templateMap.put("doLogin", properties.getProperty("ali.sms.templat.doLogin"));
        this.templateMap.put("loginErr", properties.getProperty("ali.sms.templat.loginErr"));
        this.templateMap.put("register", properties.getProperty("ali.sms.templat.register"));
        this.templateMap.put("password", properties.getProperty("ali.sms.templat.password"));
        this.templateMap.put("info", properties.getProperty("ali.sms.templat.info"));
        this.templateMap.put("warn", properties.getProperty("ali.sms.templat.custom1"));
    }

    public String getVerityIdTemplateId() {
        return templateMap.get("verityId");
    }

    public String getDoLoginIdTemplateId() {
        return templateMap.get("doLogin");
    }

    public String getLoginErrTemplateId() {
        return templateMap.get("loginErr");
    }

    public String getRegisterTemplateId() {
        return templateMap.get("register");
    }

    public String getPasswordTemplateId() {
        return templateMap.get("password");
    }

    public String getInfoTemplateId() {
        return templateMap.get("info");
    }

    public String getWarnTemplateId() {
        return templateMap.get("warn");
    }

    public String getCommonSignName() {
        return signMap.get("common");
    }

    public String getWarnSignName() {
        return signMap.get("warn");
    }

    public String getSmsDomain() {
        return smsDomain;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", SmsConfig.class.getSimpleName() + "[", "]")
                .add("smsDomain='" + smsDomain + "'")
                .add("signMap=" + signMap)
                .add("templateMap=" + templateMap)
                .toString();
    }
}
