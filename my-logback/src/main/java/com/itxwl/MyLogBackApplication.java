package com.itxwl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xueWenLiang
 * @date 2021/5/10 13:55
 * @Description 描述信息
 */
@EnableEurekaClient
@EnableTransactionManagement
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
