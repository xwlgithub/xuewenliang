package com.itxwl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author xueWenLiang
 * @date 2021/5/14 14:37
 * @Description 描述信息
 */
@EnableWebSecurity
@Configuration
public class BroverSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                //进行授权
                .authorizeRequests()
                .antMatchers("/public/demos").permitAll()
                //任何请求
                .anyRequest()
                //都需要身份认证
                .authenticated();
    }

}
