package com.ilovesshan.imusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ilovesshan.imusic.config.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 从header中取出token进行鉴权
        String token = request.getHeader(SecurityConfig.HEADER_KEY);

        // 取到的token是无效的
        if (token == null || !token.startsWith(SecurityConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }


        // token 解析
        UsernamePasswordAuthenticationToken authenticationToken = null;
        String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECURITY_KEY))
                .build()
                .verify(token.replace(SecurityConfig.TOKEN_PREFIX, ""))
                .getSubject();
        if (username != null) {
            authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
}