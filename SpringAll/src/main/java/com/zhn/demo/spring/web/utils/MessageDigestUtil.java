package com.zhn.demo.spring.web.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * introduce：MD5和SHA1加密
 *
 * @author Mr.ZhouHN
 */
public class MessageDigestUtil {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'};
    private static MessageDigest MD5;
    private static MessageDigest SHA1;

    public static MessageDigest getMD5Instance() {
        if (MD5 == null) {
            try {
                MD5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return MD5;
    }

    public static MessageDigest getSHA1Instance() {
        if (SHA1 == null) {
            try {
                SHA1 = MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return SHA1;
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String EncoderBySHA1(String str) {
        if (str == null) return null;
        MessageDigest digest = getSHA1Instance();
        digest.reset();
        digest.update(str.getBytes(StandardCharsets.UTF_8));
        return getFormattedText(digest.digest());
    }

    /**
     * Author：ZHN 说明：TODO MD5加密
     */
    public static String EncoderByMd5(String str) {
        if (str == null) return null;
        MessageDigest digest = getMD5Instance();
        digest.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = digest.digest();
        int i;
        StringBuffer buf = new StringBuffer(200);
        for (byte aByte : bytes) {
            i = aByte & 0xff;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static byte[] EncoderByMd5(byte[] input) {
        if (input == null) return null;
        return getMD5Instance().digest(input);
    }


}
