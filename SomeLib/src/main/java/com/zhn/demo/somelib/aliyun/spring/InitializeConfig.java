package com.zhn.demo.somelib.aliyun.spring;

import com.zhn.demo.somelib.aliyun.spring.bean.AppKeyInfo;
import com.zhn.demo.somelib.aliyun.spring.bean.SmsConfig;
import com.zhn.demo.somelib.aliyun.spring.bean.VmsConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringJoiner;

public class InitializeConfig {

    private static InitializeConfig ourInstance = new InitializeConfig();

    public static InitializeConfig getInstance() {
        return ourInstance;
    }

    private InitializeConfig() {
    }

    private static final String CONFIG = "/ali-config.properties";

    private static AppKeyInfo appKeyInfo;
    private static SmsConfig smsConfig;
    private static VmsConfig vmsConfig;

    static {
        InputStream in = InitializeConfig.class.getResourceAsStream(CONFIG);
        try {
            Properties properties = new Properties();
            properties.load(in);
            appKeyInfo = new AppKeyInfo(properties);
            smsConfig = new SmsConfig(properties);
            vmsConfig = new VmsConfig(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static AppKeyInfo getAppKeyInfo() {
        return appKeyInfo;
    }

    public static void setAppKeyInfo(AppKeyInfo appKeyInfo) {
        InitializeConfig.appKeyInfo = appKeyInfo;
    }

    public static SmsConfig getSmsConfig() {
        return smsConfig;
    }

    public static void setSmsConfig(SmsConfig smsConfig) {
        InitializeConfig.smsConfig = smsConfig;
    }

    public static VmsConfig getVmsConfig() {
        return vmsConfig;
    }

    public static void setVmsConfig(VmsConfig vmsConfig) {
        InitializeConfig.vmsConfig = vmsConfig;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", InitializeConfig.class.getSimpleName() + "[", "]")
                .add("AppKeyInfo=" + appKeyInfo.toString())
                .add("SmsConfig=" + smsConfig.toString())
                .add("VmsConfig=" + vmsConfig.toString())
                .toString();
    }
}
