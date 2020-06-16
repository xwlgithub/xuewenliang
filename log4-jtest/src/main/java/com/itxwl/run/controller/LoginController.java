package com.itxwl.run.controller;

import com.itxwl.run.enery.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @Auther: 薛
 * @Date: 2020/5/25 16:45
 * @Description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/vLogin")
@CrossOrigin
public class LoginController {
    @PostMapping("LoginServer")
    public String LoginServer(@RequestParam String name,@RequestParam String password) {
        User user = new User(name,password);
        return user.toString();
    }
}
