package com.itxwl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueWenLiang
 * @date 2021/6/2 9:59
 * @Description 描述信息
 */
@Configuration
@ConfigurationProperties(prefix = "mooc")
public class AppProperties {
    @Getter
    @Setter
    private Jwt jwt=new Jwt();
    @Getter
    @Setter
    public static class  Jwt{
        private String header="Authorization";
        private String prefix="Bearer ";
        private Long accessTokenExpireTime=60_000L;
        private Long refreshTokenExpireTim=30*24*3600*1000L;
    }
}
