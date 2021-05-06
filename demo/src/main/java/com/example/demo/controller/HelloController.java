package com.example.demo.controller;

import com.example.demo.enums.Demo;
import com.example.demo.enums.da;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 薛
 * @Date: 2021/1/13 15:14
 * @Description:
 */
@RestController
@RequestMapping("hello")
@Controller
public class HelloController {
    //动态加载配置文件

    @GetMapping("demo")
    public void demo(){
       List<Demo> demoList=new ArrayList<>();
       new ConcurrentHashMap<>();
       new Hashtable<>();
        while (true){
            demoList.add(new Demo());
        }
    }




}
