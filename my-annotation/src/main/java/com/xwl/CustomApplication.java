package com.xwl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xueWenLiang
 * @date 2021/4/29 16:26
 * @Description 描述信息
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xwl.mapper")
public class CustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class,args);
    }
}
