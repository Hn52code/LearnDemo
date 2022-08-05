package com.zhn.demo.basic.reg;

import java.util.regex.Pattern;

public class Demo {

    public static void main(String[] args) {
        String reg = "https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        boolean b = Pattern.matches(reg, "http://t200public.systoon.com/hrtoon-foreign-manager/api/getGateMagnetismInfo");
        System.out.println(b);
    }
}
