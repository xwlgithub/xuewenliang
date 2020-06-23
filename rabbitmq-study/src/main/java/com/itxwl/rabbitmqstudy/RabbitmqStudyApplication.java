package com.itxwl.rabbitmqstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan("com.itxwl.rabbitmqstudy.seckill.mapper")
public class RabbitmqStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqStudyApplication.class, args);
    }

}
