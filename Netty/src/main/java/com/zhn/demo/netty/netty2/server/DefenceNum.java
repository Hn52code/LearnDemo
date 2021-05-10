package com.zhn.demo.netty.netty2.server;

public class DefenceNum {

    public static String format(int num){
        if (num != 0xff)
            if (num < 10)
                return "00" + String.valueOf(num);
            else if (num < 100)
                return "0" + String.valueOf(num);
            else
                return String.valueOf(num);
        else
            return "-";
    }
}
