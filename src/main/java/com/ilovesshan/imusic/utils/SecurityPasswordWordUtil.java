package com.ilovesshan.imusic.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */
public class SecurityPasswordWordUtil {

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean verify(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}