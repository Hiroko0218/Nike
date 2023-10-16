package com.yafeng.nike.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public class JwtUtils {

    /**
     * 生成JWT
     *
     * @param claims     存入到JWT中的數據
     * @param expiration JWT過期時間
     * @param secretKey  密鑰
     * @return JWT數據
     */
    public static synchronized String generate(Map<String, Object> claims, Date expiration, String secretKey) {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 解析JWT
     *
     * @param jwt       JWT數據
     * @param secretKey 生成JWT時使用的密鑰
     * @return 解析結果
     */
    public static synchronized Claims parse(String jwt, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
