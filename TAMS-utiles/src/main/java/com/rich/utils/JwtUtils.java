package com.rich.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

//依赖：
//        <!--  WT令牌-->
//        <dependency>
//            <groupId>io.jsonwebtoken</groupId>
//            <artifactId>jjwt</artifactId>
//            <version>0.9.1</version>
//        </dependency>

public class JwtUtils {

    private static String signKey = "RichTAMS";
    private static Long expire = 43200000L;//12h

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)//设置payload（负载），可以放一些用户信息
                .signWith(SignatureAlgorithm.HS256, signKey)//HS256签名算法 和 密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))//设置过期时间
                .compact();//生成JWT令牌
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//设置密钥
                .parseClaimsJws(jwt)//解析JWT令牌
                .getBody();//获取JWT第二部分负载 payload 中存储的内容
        return claims;
    }
}
