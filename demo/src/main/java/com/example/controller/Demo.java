package com.example.controller;

import com.example.annotation.MyConfigurationProperties;
import com.example.annotation.MyProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueWenLiang
 * @date 2021/4/25 10:31
 * @Description 描述信息
 */
@RestController
@AllArgsConstructor
@RequestMapping("demos")
public class Demo {
    private MyConfigurationProperties myConfigurationProperties;
    private MyProperties myProperties;

    @GetMapping("demoss")
    public ResponseEntity<Object> getResponse(){
        System.out.println(myProperties.toString());
        System.out.println(myProperties.getPort());
        System.out.println(myConfigurationProperties.toString());
//        System.out.println(myConfigurationProperties.getConfigurations().toString());
//        System.out.println(myConfigurationProperties.getConfigurations().getUrl());
//        System.out.println(myConfigurationProperties.getConfigurations().getPort());
        return ResponseEntity.ok(myProperties.toString());
    }
}
