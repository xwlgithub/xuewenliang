package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 薛
 * @Date: 2021/1/13 15:14
 * @Description:
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @GetMapping("getNum")
    public String getNum(){
        Long ss = redisTemplate.opsForValue().increment("ss");
        return "婷婷爱薛少"+ss+"次";
    }

}
