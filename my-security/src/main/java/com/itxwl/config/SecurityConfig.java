package com.itxwl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itxwl.filter.RestUserNamePasswordFilter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
//@Import(SecurityProblemSupport.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final SecurityProblemSupport securityProblemSupport;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录
        http
//                .exceptionHandling().accessDeniedHandler(securityProblemSupport)
//                .authenticationEntryPoint(securityProblemSupport)
//                .and()
                .formLogin()
                .loginPage("/login")
                //指定form表单提交url
                .loginProcessingUrl("/login")
                //默认成功之后访问url-》如果配置了successHandler首先执行successHandler
                .defaultSuccessUrl("/index")
                .successHandler(jsonAuthenticationSuccessHandler())
                .failureHandler(jsonAuthenticationFailureHandler())
                .and()
                .authorizeRequests()
                //鉴权放行
                .antMatchers("/index","/login","/authorizes/**","/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic();
                //添加过滤器
               // .and().addFilterAt(userNamePasswordFilter(), UsernamePasswordAuthenticationFilter.class);
//                .and()
                //添加记住我
               // .rememberMe().tokenValiditySeconds(3000 * 10).rememberMeCookieName("xwl");
        http.csrf().disable();
        ;
    }

    /**
     * Username 认证过滤器
     * @return
     * @throws Exception
     */
    @Bean
    public RestUserNamePasswordFilter userNamePasswordFilter() throws Exception {
        RestUserNamePasswordFilter filter = new RestUserNamePasswordFilter();
        filter.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(jsonAuthenticationFailureHandler());
        filter.setFilterProcessesUrl("/api/xwlLogin");
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    /**
     * 认证失败处理
     * @return
     */
    private AuthenticationFailureHandler jsonAuthenticationFailureHandler() {
        return (httpServletRequest,httpServletResponse,authenticationException) ->{
            ObjectMapper om=new ObjectMapper();
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setCharacterEncoding("utf-8");
            Map<String, Object> map = new HashMap<>();
            map.put("title","认证失败");
            map.put("details",authenticationException.getMessage());
            httpServletResponse.getWriter().println(om.writeValueAsString(map));
        };
    }

    /**
     * 认证成功处理
     * @return
     */
    private AuthenticationSuccessHandler jsonAuthenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            ObjectMapper objectMapper=new ObjectMapper();
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.getWriter().println(objectMapper.writeValueAsString(authentication));
        };
    }

    /**
     * inMemoryAuthentication 基于内存
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                //.password(passwordEncoder().encode("123456789"))
                .password("{bcrypt}$2a$10$Pp1.wmvTYxkSsP4ahWHVKOrlu9ti.bjqX3k0n9a70H9jQ8F9oaFZ2")
                .roles("user", "admin")
                .and()
                .withUser("users")
                .password("{SHA-1}{eABYh0gkPCjzj2F7EMz5Q8F05/5o/1mit9Y1n4d4dns=}421b9d0311ab12ea175d206468266b46ae147a3d")
                .roles("user", "admin")
        ;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**", "/error")
                //放行静态资源
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        ;
    }
    /**
     * idForEncode决定密码编码器的类型,idToPasswordEncoder决定判断匹配时兼容的类型
     * 而且idToPasswordEncoder必须包含idForEncode(不然加密后就无法匹配了)
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        String idForDefault="bcrypt";
        Map<String, PasswordEncoder> map = new HashMap<>();
        map.put(idForDefault,new BCryptPasswordEncoder());
        map.put("SHA-1",new MessageDigestPasswordEncoder("SHA-1"));
        return new DelegatingPasswordEncoder(idForDefault,map);
        //return new BCryptPasswordEncoder();
    }

}
