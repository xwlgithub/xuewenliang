package com.xwl.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author xueWenLiang
 * @date 2021/4/30 14:09
 * @Description 描述信息
 */
@Configuration
@Component
@SuppressWarnings("all")
public class RequestMvcConfigutaionFilter  implements WebMvcConfigurer {
    /**
     * 自定义注解控制器集合体处理
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //注入用户信息注解控制器
        resolvers.add(new XwlUserArgumentResolver());
    }
}
