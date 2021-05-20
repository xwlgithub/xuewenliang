package com.itxwl.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author xueWenLiang
 * @date 2021/5/15 10:03
 * @Description 描述信息
 */
@RestController
@RequestMapping("/api")
public class UserResource {


    @GetMapping("greeting")
    public String greeting(){
        return "hello world";
    }
    @PostMapping("getName")
    public String greetings(){
        return "hello world";
    }
    @GetMapping("logins")
    public String login(){
        return "logins";
    }

    @PostMapping("xwlLogin")
    public String xwlLogin(@RequestParam("username")String username,@RequestParam("password") String password){

        return "hello world xwlLogin";
    }

    @GetMapping("/principal")
    public String getPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
