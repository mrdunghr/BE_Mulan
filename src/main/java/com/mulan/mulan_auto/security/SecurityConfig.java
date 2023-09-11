package com.mulan.mulan_auto.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Tắt CSRF (Cross-Site Request Forgery) cho ứng dụng RESTful API.
            .authorizeRequests()
            .antMatchers("/api/v1/customers/**").permitAll() // Cho phép truy cập các endpoint công khai mà không cần xác thực.
            .anyRequest().authenticated(); // Tất cả các request khác đều yêu cầu xác thực JWT.
    }
}