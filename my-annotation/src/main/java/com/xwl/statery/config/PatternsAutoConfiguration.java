package com.xwl.statery.config;

import com.xwl.statery.appint.BusinessHandlerChoose;
import com.xwl.statery.factory.BusinessProcessFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author xueWenLiang
 * @date 2021/9/2 14:55
 * @Description 配置类自动注入
 */
@Configuration
@Slf4j
public class PatternsAutoConfiguration {


    @Bean
    public BusinessHandlerChoose businessHandlerChooser(List<BusinessProcessFactory> businessProcessFactoryList ){
        System.out.println("记载------"+businessProcessFactoryList.toString());
        BusinessHandlerChoose businessHandlerChoose = new BusinessHandlerChoose();
        businessHandlerChoose.setBusinessHandlerMap(businessProcessFactoryList);
        return businessHandlerChoose;
    }
}
