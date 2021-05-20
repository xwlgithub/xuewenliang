package com.itxwl.rest;

import com.itxwl.domain.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:07
 * @Description 描述信息
 */
@RestController
@RequestMapping("/authorize")
public class AuthorizeResource {



    @PostMapping("/register")
    public UserDto register(@Valid @RequestBody UserDto userDto){
        return  userDto;
    }
}
