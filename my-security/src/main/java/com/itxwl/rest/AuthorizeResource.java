package com.itxwl.rest;

import com.itxwl.domain.Auth;
import com.itxwl.domain.dto.LoginDto;
import com.itxwl.domain.dto.UserDto;
import com.itxwl.service.UserService;
import com.itxwl.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:07
 * @Description 描述信息
 */
@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
public class AuthorizeResource {
private final UserService userService;
private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody UserDto userDto){
        return  userDto;
    }


    @PostMapping("/token")
    public Auth login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getUsername(),loginDto.getPassword());
    }
}
