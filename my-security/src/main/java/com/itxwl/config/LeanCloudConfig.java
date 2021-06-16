package com.itxwl.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import cn.leancloud.core.AVOSCloud;
import javax.annotation.PostConstruct;

/**
 * @author xueWenLiang
 * @date 2021/6/7 16:04
 * @Description 描述信息
 */
@Configuration
@RequiredArgsConstructor
public class LeanCloudConfig {

    private final AppProperties appProperties;

    @PostConstruct()
    public void initialize() {
//        if (env.acceptsProfiles(Profiles.of("prod"))) {
//            AVOSCloud.setLogLevel(AVLogger.Level.ERROR);
//        } else {
//            AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);
//        }
        //AVOSCloud.initialize(appProperties.getLeanCloud().getAppId(), appProperties.getLeanCloud().getAppKey());
    }
}
