package com.itxwl.rest;

import com.itxwl.domain.User;
import com.itxwl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author xueWenLiang
 * @date 2021/5/15 10:03
 * @Description 描述信息
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
private final UserService userService;

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

    @GetMapping("/principals")
    public String getPrincipal(Principal principal){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/principal")
    public String getCurrentPrincipalName(Principal principal) {
        return principal.getName();
    }


    @GetMapping("/users/{username}")
    public String getCurrentUsername(@PathVariable String username) {
        return username;
    }
    //authentication.name.equals(returnObject.username)
    @PostAuthorize("authentication.name.equals(returnObject.username)")
    @GetMapping("/users/by-email/{email}")
    public User getUserEmail(@PathVariable String email){
        User user = new User();
        user.setEmail(email);
        return user;
    }
}
