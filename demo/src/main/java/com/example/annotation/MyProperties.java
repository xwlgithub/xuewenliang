package com.example.annotation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xueWenLiang
 * @date 2021/4/25 10:22
 * @Description 描述信息
 */
@Getter
@Setter
@Component
/**
 * 自动装配
 */
@ConfigurationProperties(MyProperties.PREFIX)
public class MyProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "xwl.prefix";
    private String url;
    private String port;
}
