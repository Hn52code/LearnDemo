package com.zhn.demo.somelib.aliyun.spring;


import com.alibaba.fastjson.JSONObject;
import com.zhn.demo.somelib.aliyun.spring.api.Sms;
import com.zhn.demo.somelib.aliyun.spring.api.Vms;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SmsTest {

    @Test
    public void testSms() {
        Sms sms = new Sms();
//        System.out.println(sms.sendSmsOnIDVerity("4875", "18888888888"));
//        System.out.println(sms.sendSmsOnInfo("4875", "18888888888"));
//        System.out.println(sms.sendSmsOnLogin("4875", "18888888888"));
//        System.out.println(sms.sendSmsOnLoginErr("4875", "18888888888"));
//        System.out.println(sms.sendSmsOnRegister("4875", "18888888888"));
//        System.out.println(sms.sendSmsOnPassword("4875", "18888888888"));
//        List<String> list = new ArrayList<>();
//        list.add("18888888888");
//        System.out.println(sms.sendSmsOnWarn(map, list));
        String uri = "https://code.com";

    }

    @Test
    public void testVms() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "报警");
        map.put("devId", "10057473");
        map.put("warn", "烟感丢失");

        System.out.println(JSONObject.toJSONString(map));
        Vms vms = new Vms();
        System.out.println(vms.sendTextCallOnMan("15977522022", map));
    }

}
