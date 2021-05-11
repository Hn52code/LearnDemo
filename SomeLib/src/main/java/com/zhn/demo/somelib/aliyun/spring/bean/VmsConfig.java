package com.zhn.demo.somelib.aliyun.spring.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringJoiner;

public class VmsConfig {

    private String vmsDomain;
    private String vmsPhone;
    private Map<String, String> map = new HashMap<>();

    public VmsConfig(Properties properties) {
        this.vmsDomain = properties.getProperty("ali.vms.domain");
        this.vmsPhone = properties.getProperty("ali.vms.phone");
        this.map.put("warn.man", properties.getProperty("ali.vms.template.custom1.man"));
        this.map.put("warn.woman", properties.getProperty("ali.vms.template.custom1.woman"));
    }


    public String getVmsWarnManTemplateId() {
        return map.get("warn.man");
    }

    public String getVmsWarnWomanTemplateId() {
        return map.get("warn.woman");
    }

    public String getVmsDomain() {
        return vmsDomain;
    }


    public String getVmsPhone() {
        return vmsPhone;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", VmsConfig.class.getSimpleName() + "[", "]")
                .add("vmsDomain='" + vmsDomain + "'")
                .add("vmsPhone='" + vmsPhone + "'")
                .toString();
    }
}
