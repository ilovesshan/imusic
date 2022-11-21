package com.ilovesshan.imusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ilovesshan.imusic.beans.entity.Role;
import com.ilovesshan.imusic.beans.entity.User;
import com.ilovesshan.imusic.config.SecurityConfig;
import com.ilovesshan.imusic.service.UserService;
import com.ilovesshan.imusic.utils.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
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


        // token解析 和 绑定用户角色
        UsernamePasswordAuthenticationToken authenticationToken = null;
        String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECURITY_KEY))
                .build()
                .verify(token.replace(SecurityConfig.TOKEN_PREFIX, ""))
                .getSubject();
        if (username != null) {
            // 根据用户名查询用户角色信息
            User selectedUser = userService.selectById(TokenUtils.getUserId(token));
            List<Role> roleList = selectedUser.getRoleList();

            // 绑定用户角色
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            roleList.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
            authenticationToken = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
}