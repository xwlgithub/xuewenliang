package com.itxwl.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @Auther: 薛
 * @Date: 2020/4/30 13:23
 * @Description:
 */
@SpringBootApplication()
public class LogFourDebugApplicatiom {
    public static void main(String[] args) {
        SpringApplication.run(LogFourDebugApplicatiom.class, args);
    }
}
