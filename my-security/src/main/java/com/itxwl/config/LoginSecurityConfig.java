//package com.itxwl.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author xueWenLiang
// * @date 2021/5/20 11:03
// * @Description 描述信息
// */
//@Configuration
//@Order(100)
//public class LoginSecurityConfig  extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http    .formLogin().and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
////      http .formLogin()
////                .loginPage("/login")
////                //指定form表单提交url
////                .loginProcessingUrl("/login")
////                //默认成功之后访问url-》如果配置了successHandler首先执行successHandler
////                .defaultSuccessUrl("/index")
////                .and()
////                .authorizeRequests()
////                //鉴权放行
////                //.antMatchers("/index","/login","/authorize/**").permitAll()
////                .anyRequest()
////                .authenticated();
//    }
//    /*      http .formLogin()
//                .loginPage("/login")
//                //指定form表单提交url
//                .loginProcessingUrl("/login")
//                //默认成功之后访问url-》如果配置了successHandler首先执行successHandler
//                .defaultSuccessUrl("/index")
//                .and()
//                .authorizeRequests()
//                //鉴权放行
//                .antMatchers("/index","/login","/authorize/**").permitAll()
//                .anyRequest()
//                .authenticated();*/
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/public/**", "/error")
//                //放行静态资源
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//        ;
//    }
//}
