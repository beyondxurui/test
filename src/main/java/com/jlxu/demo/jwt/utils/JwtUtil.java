package com.jlxu.demo.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.jlxu.demo.jwt.dto.UserDto;

/**
 * jwt工具类
 *
 * @author xjl
 * @since 2019-11-02
 */
public class JwtUtil {
    /**
     * 生成token
     *
     * @param userDto userDto
     * @return token
     */
    public static String createToken(UserDto userDto) {
        String token = null;
        if (userDto != null) {
            token = JWT.create().withKeyId(userDto.getId().toString()).sign(Algorithm.HMAC256(userDto.getPassword()));
        }
        return token;
    }

    /**
     * @param token token
     * @return true:通过/false:不通过
     */
    public boolean verifyToken(String token) {
        boolean flag = false;
        if (token != null) {
            //TODO:持久化校验省略
            String keyId = JWT.decode(token).getKeyId();
//            JWT.require(Algorithm.HMAC256())
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(keyId)).build();
            jwtVerifier.verify(token);
        }
        return flag;
    }

}
