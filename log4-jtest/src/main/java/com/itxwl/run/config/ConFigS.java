package com.itxwl.run.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: 薛
 * @Date: 2020/5/25 16:48
 * @Description:
 */
@Configuration
public class ConFigS extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(new LoginInterpretor()).excludePathPatterns("/**");
    }
}
