package com.itxwl.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueWenLiang
 * @date 2021/6/7 15:13
 * @Description 描述信息
 */
@Configuration
@RequiredArgsConstructor
public class AliConfig {


    private final AppProperties appProperties;

    @Bean
    public IAcsClient iAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",
                appProperties.getAli().getApiKey(),
                appProperties.getAli().getApiSecret());
        return new DefaultAcsClient(profile);
    }
}
