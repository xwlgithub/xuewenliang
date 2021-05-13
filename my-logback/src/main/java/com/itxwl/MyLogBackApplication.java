package com.itxwl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xueWenLiang
 * @date 2021/5/10 13:55
 * @Description 描述信息
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.itxwl.mapper")
public class MyLogBackApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(MyLogBackApplication.class,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
