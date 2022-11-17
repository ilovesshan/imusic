package com.ilovesshan.imusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilovesshan.imusic.common.R;
import com.ilovesshan.imusic.config.SecurityConfig;
import com.ilovesshan.imusic.beans.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 获取到用户名和密码 交给authenticationManager管理
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            if (user == null || user.getUsername() == null || user.getPassword() == null) {
                throw new RuntimeException("用户名或密码布不正确");
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.emptyList());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 表示 attemptAuthentication已经校验通过 生成token响应给客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = ((User) authResult.getPrincipal()).getId();
        String username = ((User) authResult.getPrincipal()).getUsername();
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRE))
                .sign(Algorithm.HMAC512(SecurityConfig.SECURITY_KEY.getBytes(StandardCharsets.UTF_8)));

        // 构建响应给客户端的数据
        HashMap<Object, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("username", username);
        data.put("token", token);
        String responseJson = new ObjectMapper().writeValueAsString(R.builder().code(200).message(R.SUCCESS_MESSAGE_LOGIN).data(data).build());
        response.getWriter().print(responseJson);
    }
}