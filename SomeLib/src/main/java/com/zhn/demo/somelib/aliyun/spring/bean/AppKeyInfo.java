package com.zhn.demo.somelib.aliyun.spring.bean;

import java.util.Properties;
import java.util.StringJoiner;

public class AppKeyInfo {

    private String accessKeyId;
    private String accessKeSecret;
    private String regionId;

    public AppKeyInfo(Properties properties) {
        this.accessKeyId = properties.getProperty("ali.accessKeyId");
        this.accessKeSecret = properties.getProperty("ali.accessKeySecret");
        this.regionId = properties.getProperty("ali.regionId");
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeSecret() {
        return accessKeSecret;
    }

    public String getRegionId() {
        return regionId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AppKeyInfo.class.getSimpleName() + "[", "]")
                .add("accessKeyId='" + accessKeyId + "'")
                .add("accessKeSecret='" + accessKeSecret + "'")
                .add("regionId='" + regionId + "'")
                .toString();
    }
}
