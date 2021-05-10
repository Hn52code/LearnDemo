package com.zhn.demo.somelib.jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.Base64Utils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class JJwtDemo {

    /**
     * header  头不需要设置
     * payload  主题 可以包含用户信息，但不要包含密码等，对称加密 暴露 key 后容易泄露信息
     * signature 加密
     */
    public static void main(String[] args) {


        String pat = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        boolean matches = Pattern.matches(pat, "http://wwbaidu.com/qq");
        System.out.println(matches);

        // 在配置中最好放置加密密钥，而非明文

//        /* 假设下为实际key明文*/
//        String my = "#1keA9384.@dN4]";
//
//        // 为了安全起见
//        /* 配置中存放的明文加密文 */
//        String encodeKey = Base64Utils.encodeToUrlSafeString(my.getBytes());
//        System.out.println(encodeKey);
//
//        byte[] decodeKey = Base64Utils.decodeFromUrlSafeString(encodeKey);
//
//        System.out.println(new String(decodeKey));
//
//
//        Key key = new SecretKeySpec(decodeKey, SignatureAlgorithm.HS512.getJcaName());
//
//        System.out.println(key.toString());
//
//        long millis = System.currentTimeMillis();
//        System.out.println(millis);
//        String jwt = Jwts.builder()
//                .setId(UUID.randomUUID().toString())     // 版本
//                .setIssuer("Pa Tech.")                   // 发行人
//                .setAudience("Th3 User")                 // 受众人
//                .setSubject("Th3 Api V1.0")                    // 主题
//                .claim("userId",13257)
//                .setIssuedAt(Date.from(Instant.now()))      // 签发时间
//                .setNotBefore(Date.from(Instant.now()))       // 生效时间
//                .setExpiration(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + 4000)))   // 过期时间
//                .signWith(SignatureAlgorithm.HS256, key)       // 进行加密
//                .compact();         // 生成字符串
//        System.out.println(jwt);
//
//        Thread.sleep(2000);
//
//        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
//        System.out.println(body.toString());
//        System.out.println(body.get("userId",Integer.class));
    }

}
