package com.itxwl.config;

import com.sendgrid.SendGrid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueWenLiang
 * @date 2021/6/7 16:19
 * @Description 描述信息
 */
@RequiredArgsConstructor
@Configuration
public class EmailConfig {
    private final AppProperties appProperties;

    //@ConditionalOnProperty(prefix = "mooc.email-provider", name = "api-key")
    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(appProperties.getEmailProvider().getApiKey());
    }
}
