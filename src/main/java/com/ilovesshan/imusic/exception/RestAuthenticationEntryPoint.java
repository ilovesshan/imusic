package com.ilovesshan.imusic.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilovesshan.imusic.common.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // 用户登录时身份认证未通过
        String responseMessage = "";
        if (authException instanceof BadCredentialsException) {
            // 用户登录时身份认证失败
            responseMessage = R.ERROR_BAD_CREDENTIALS;
        } else if (authException instanceof InsufficientAuthenticationException) {
            // 缺少请求头参数,Authorization传递是token值所以参数是必须的(也有可能是用户名或密码错误)
            responseMessage = R.ERROR_INSUFFICIENT_AUTHENTICATION;
        } else if (authException instanceof AccountExpiredException) {
            //账户过期
            responseMessage = R.ERROR_ACCOUNT_EXPIRED;
        } else if (authException instanceof CredentialsExpiredException) {
            //证书过期
            responseMessage = R.ERROR_CREDENTIALS_EXPIRED;
        } else if (authException instanceof DisabledException) {
            //账户不可用
            responseMessage = R.ERROR_DISABLED;
        } else if (authException instanceof LockedException) {
            //账户锁定
            responseMessage = R.ERROR_LOCKED;
        } else {
            // 用户token无效
            responseMessage = R.ERROR_AUTHORIZATION_FAILURE;
        }

        String responseJson = new ObjectMapper().writeValueAsString(R.builder().code(401).message(responseMessage).build());
        response.getWriter().print(responseJson);
        response.getWriter().flush();
    }
}
