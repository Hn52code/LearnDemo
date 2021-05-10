package com.zhn.demo.netty.netty3;

import java.util.Arrays;

/**
 * 各种进制与编码之间的转换：
 * 16进制与字符串互转,ASCII版和Unicode版
 * byte转换成int
 *
 * @author zhn  ASCII,Unicode编码表
 */
public class ByteUtil {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // <<--------------------16进制与字符互转-------start--------------->>//

    /**
     * 说明：字符转换为16进制，乱码字符不可用该方法
     * 原理：先将字符串中的每个字符转换为对应的ASCII中数字值(2进制或10进制),再转为16进制
     */
    public static String stringToHexString(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i);
            buffer.append(Integer.toHexString(ch));
//            buffer.append("");
        }
        return buffer.toString();
    }

    /**
     * 说明：字符转换为16进制,unicode可行,包含乱码字符。
     * 原理：根据&运算 检索对应的16进制;
     * byte，字节类型，占用8bit空间，可用8位2进制数表示；十六进制，每位占有4bit空间，可用4位2进制数表示。
     */
    public static String bytesToHexString(byte[] bytes) {
        int bit;
        String str = "";
        for (int i : bytes) {
            bit = (i & 0x0f0) >> 4;
            str += HEX_CHAR[bit];
            bit = i & 0x0f;
            str += HEX_CHAR[bit];
        }
        return str;
    }

    /**
     * 说明：16进制转为字符转
     * 原理：读取两个16进制的字符，两个字符位有16位bite
     *
     * @param str16 16进制的字符串，两位表示16进制的数，即8位，表示一个字符
     */
    public static String hexStringToString(String str16) {
        if (str16 == null || str16.equals(""))
            return null;
        str16 = str16.replace(" ", "");
        byte[] baKeyword = new byte[str16.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            // 将字符转换为对应的数字值，此时是int类型-->>32位
            int j = Integer.parseInt(str16.substring(i * 2, i * 2 + 2), 16);
            // 与0xff进行与运算 ，因为字节是8位，去除高位补码。
            baKeyword[i] = (byte) (0xff & j);
        }
        return new String(baKeyword);
    }
    // <<--------------------16进制与字符互转-------end----------------->>//

    // <<--------------------byte与int互转-------start--------------->>//

    /**
     * 将int转为高字节在前，低字节在后的byte数组
     * b[0] = 11111111(0xff) & 01100001
     * b[1] = 11111111(0xff) & (n >> 8)00000000
     * b[2] = 11111111(0xff) & (n >> 16)00000000
     * b[3] = 11111111(0xff) & (n >> 24)00000000
     */
    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n >> 24 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[3] = (byte) (n & 0xff);
        return b;
    }

    public static int bytesToInt(byte[] b) {
        if (b.length != 4)
            return -1;
        return ((b[0] & 0xff) << 24) | ((b[1] & 0xff) << 16) | ((b[2] & 0xff) << 8) | (b[3] & 0xff);
    }

    // 字符串的每一个字符数值相加
    public static int StringToInt(String str) {
        if (str == null || str.equals(""))
            return 0;
        str = str.trim();
        byte[] bs = str.getBytes();
        return byte2Int(bs);
    }

    // 字节数组每一项相加之和
    public static int byte2Int(byte[] b) {
        int sum = 0;
        if (b != null)
            for (int i : b)
                sum += i;
        return sum;
    }

    /**
     * 随机获取指定len/2长度的字符串，适用于unicode编码
     *
     * @param len
     * @return
     */
    public static byte[] randomBytes(int len) {
        int length = len / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int h = (int) (Math.random() * 16);
            int j = (int) (Math.random() * 16);
            bytes[i] = (byte) ((h << 4 | j) & 0xff);
        }
        return bytes;
    }

    /**
     * 将字节码数组转换为字符串，除去值为0xff的字节码
     * @param bytes 字节码数组
     * @return String
     */
    public static String byte2Mac(byte[] bytes) {
        int bit = 0;
        String str = "";
        for (byte i : bytes) {
            bit = i & 0xff;
            if (bit != 0xff) str += bit;
        }
        return str;
    }


    /**
     * 将字节码数组转换为字符串，除去值为0xff 以及其后面的的字节码
     *
     * @param bytes 字节码数组 16进制 0x30 转成数值 0 该值对应ASCII 0X30表示数字0
     * @return String
     */
    public static String byte2Mac2(byte[] bytes) {
        byte[] b2 = {};
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) == 0xff) {
                b2 = Arrays.copyOfRange(bytes, 0, i);
                return new String(b2);
            }
        }
        return new String(bytes);
    }

}
