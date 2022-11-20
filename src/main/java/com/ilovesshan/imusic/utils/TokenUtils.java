package com.ilovesshan.imusic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ilovesshan.imusic.config.SecurityConfig;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/20
 * @description:
 */

public class TokenUtils {

    public static String generatorToken(String userId, String username) {
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRE))
                .sign(Algorithm.HMAC512(SecurityConfig.SECURITY_KEY.getBytes(StandardCharsets.UTF_8)));
    }

    public static String getUserId(String token) {
        return JWT.require(Algorithm.HMAC512(SecurityConfig.SECURITY_KEY))
                .build()
                .verify(token.replace(SecurityConfig.TOKEN_PREFIX, ""))
                .getClaim("userId").asString();
    }

    public static String getUsername(String token) {
        return JWT.require(Algorithm.HMAC512(SecurityConfig.SECURITY_KEY))
                .build()
                .verify(token.replace(SecurityConfig.TOKEN_PREFIX, ""))
                .getClaim("username").asString();
    }
}
