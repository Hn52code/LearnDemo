package com.zhn.demo.shiro.hash;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class TestHash {

    public static void main(String[] args) {
        // 原始密码
        String source = "12345";
        // 随机字符
        String nonce = "asdf";
        // 散列次数
        int count = 2;
        Md5Hash md5Hash = new Md5Hash(source, nonce, count);

        System.out.println(md5Hash.toString());

        SimpleHash simpleHash = new SimpleHash("md5", source, nonce, count);

        System.out.println(simpleHash.toString());

    }

}
