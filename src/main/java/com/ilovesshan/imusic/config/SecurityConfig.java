package com.ilovesshan.imusic.config;

import com.ilovesshan.imusic.exception.RestAuthenticationEntryPoint;
import com.ilovesshan.imusic.filter.JwtAuthorizationFilter;
import com.ilovesshan.imusic.filter.JwtAuthenticationFilter;
import com.ilovesshan.imusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/14
 * @description:
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserService userService;

    public static final String SECURITY_KEY = "imusic";
    public static final String HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/users/";
    public static final long EXPIRE = 1000 * 60 * 60 * 24; // 过期时间 一天

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
