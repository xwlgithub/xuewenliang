package com.example.demo.controller;

import com.example.demo.enums.Demo;
import com.example.demo.enums.da;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2021/1/13 15:14
 * @Description:
 */
@RestController
@RequestMapping("hello")
@Controller
public class HelloController {

    @GetMapping("demo")
    public void demo(){
       List<Demo> demoList=new ArrayList<>();
        while (true){
            demoList.add(new Demo());
        }
    }




}
