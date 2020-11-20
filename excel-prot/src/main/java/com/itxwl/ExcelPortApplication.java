package com.itxwl;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Auther: 薛
 * @Date: 2020/11/20 14:11
 * @Description:
 */
@EnableEurekaServer
@EnableDiscoveryClient
@SpringCloudApplication
public class ExcelPortApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExcelPortApplication.class,args);
    }
}
