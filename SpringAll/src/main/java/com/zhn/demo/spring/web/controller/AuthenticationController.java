package com.zhn.demo.spring.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhn.demo.spring.web.utils.MessageDigestUtil;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private static String token = "abcdefghijkmlnopqrstuvwxyz";//用户自定义token和OneNet第三方平台配置里的token一致
    private static String aeskey = "whBx2ZwAU5LOHVimPj1MPx56QRe3OsGGWRe4dr17crV";//aeskey和OneNet第三方平台配置里的token一致

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/receive")
    public String check(String msg, String nonce, String signature, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.print(entry.getKey() + " ");
            for (String s : entry.getValue()) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        boolean check = check(token, nonce, msg, signature);
        if (check) return msg;
        return "error";
    }

    @PostMapping("/receive")
    public String receive(@RequestBody String body) {
        ReceiveBody receiveBody = JSONObject.parseObject(body, ReceiveBody.class);
        System.out.println(body);
        /*-----明文模式------*/
        boolean check1 = check(token, receiveBody.getNonce(), receiveBody.getMsg().toString(),
                receiveBody.getMsgSignature());
        if (check1) {

        }
        /*-----加密模式------*/
        /*boolean check2 = check(token, receiveBody.getNonce(), receiveBody.getEnc_msg().toString(),
                receiveBody.getMsgSignature());
        if (check2) {
            String msgStr = decodeMsg(receiveBody, aeskey);
        }*/
        return "ok";
    }

    private String decodeMsg(ReceiveBody receiveBody, String aeskey) {
        byte[] encBytes = Base64Utils.decodeFromString(receiveBody.getEnc_msg().toString());
        byte[] aeskeyBytes = Base64Utils.decodeFromString(aeskey + "=");
        SecretKey secretKey = new SecretKeySpec(aeskeyBytes, 0, 32, "AES");
        Cipher cipher = null;
        byte[] allmsg = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(aeskeyBytes, 0, 16));
            allmsg = cipher.doFinal(encBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | BadPaddingException | InvalidKeyException
                | IllegalBlockSizeException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        byte[] msgLenBytes = new byte[4];
        System.arraycopy(allmsg, 16, msgLenBytes, 0, 4);
        int msgLen = (msgLenBytes[0] & 0xFF) << 24 + (msgLenBytes[1] & 0xFF) << 16 +
                (msgLenBytes[2] & 0xFF) << 8 + (msgLenBytes[3] & 0xFF);
        byte[] msg = new byte[msgLen];
        System.arraycopy(allmsg, 20, msg, 0, msgLen);
        return new String(msg);
    }

    private boolean check(String token, String nonce, String msg, String signature) {
        byte[] bytes = new byte[token.length() + 8 + msg.length()];
        System.arraycopy(token.getBytes(), 0, bytes, 0, token.length());
        System.arraycopy(nonce.getBytes(), 0, bytes, token.length(), 8);
        System.arraycopy(msg.getBytes(), 0, bytes, token.length() + 8, msg.length());
        String completeSign = Base64Utils.encodeToString(MessageDigestUtil.EncoderByMd5(bytes));
        return completeSign.equals(signature);
    }

    class ReceiveBody {
        private Object msg;              // 明文模式消息
        private Object enc_msg;         // 加密模式消息
        private String nonce;           // 用于计算消息摘要的随机串
        private String msgSignature;   // 消息摘要

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public Object getEnc_msg() {
            return enc_msg;
        }

        public void setEnc_msg(Object enc_msg) {
            this.enc_msg = enc_msg;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getMsgSignature() {
            return msgSignature;
        }

        public void setMsgSignature(String msgSignature) {
            this.msgSignature = msgSignature;
        }

        @Override
        public String toString() {
            return "ReceiveBody{" +
                    "msg=" + msg +
                    ", enc_msg=" + enc_msg +
                    ", nonce=" + nonce +
                    ", msgSignature='" + msgSignature + '\'' +
                    '}';
        }
    }

}
