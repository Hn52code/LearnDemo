package com.zhn.demo.webexample.common;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


public class JwtUtil {

    private final String key = "nqwPj@Slr%aM13";
    //    private final String id = "v1.0";                // id/版本
    private final String issuer = "Pa Tech.";             // 发行人
    private final String audience = "Th3 User";           // 对象
    private final String subject = "Th3 Api v1.0";       // 主题
    private final long notBefore = 1;                           // 生效时间 当前延迟
    // private long issuerAt = 0;                           // 签发时间
//    private final long express = 60 * 60 * 2;         // 两小时
    private final long express = 10 ;               // 10秒

    private SecretKey secretKey;

    private SecretKey getSecretKey() {
        if (secretKey == null) {
            synchronized (this) {
                if (secretKey == null)
                    secretKey = new SecretKeySpec(key.getBytes(),
                            SignatureAlgorithm.HS512.getJcaName());
            }
        }
        return secretKey;
    }

    /* 获取token */
    public String getToken(Map<String, Object> map) {
        Instant instant = Instant.now();
        JwtBuilder jwtBuilder = Jwts.builder();
        map.forEach(jwtBuilder::claim);
        return jwtBuilder.setId(UUID.randomUUID().toString())           // 版本
                .setIssuer(issuer)                                      // 发行人
                .setAudience(audience)                                  // 受众人
                .setSubject(subject)                                    // 主题
                .setIssuedAt(Date.from(instant))                        // 签发时间
                .setNotBefore(Date.from(instant.plusSeconds(notBefore))) // 生效时间
                .setExpiration(Date.from(instant.plusSeconds(express)))  // 过期时间
                .signWith(SignatureAlgorithm.HS256, getSecretKey())     // 进行加密
                .compact();                                             // 生成字符串
    }

    public Claims parse(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
    }

    public long getExpressTime() {
        return express;
    }

}
