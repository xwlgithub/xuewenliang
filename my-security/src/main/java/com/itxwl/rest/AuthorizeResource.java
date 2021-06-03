package com.itxwl.rest;

import com.itxwl.domain.Auth;
import com.itxwl.domain.User;
import com.itxwl.domain.dto.LoginDto;
import com.itxwl.domain.dto.UserDto;
import com.itxwl.service.UserService;
import com.itxwl.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

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


    /**
     * 获取token
     * @param loginDto
     * @return
     */
    @PostMapping("/token")
    public Auth login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getUsername(),loginDto.getPassword());
    }

    /**
     * 刷新token
     * @param accessToken
     * @param refreshToken
     * @return
     */
    @PostMapping("/token/refresh")
    public Auth refreshToken(@RequestParam String accessToken, @RequestParam String refreshToken) {
        val PREFIX = "Bearer ";
        val accessTokens = accessToken.replace(PREFIX, "");
        //&& jwtUtil.validateAccessTokenWithoutExpiration(accessToken)
        if (jwtUtil.validateRefreshToken(refreshToken) && jwtUtil.validateAccessTokenWithoutExpiration(accessTokens)) {
            return new Auth(jwtUtil.buildAccessTokenWithRefreshToken(refreshToken), refreshToken);
        }
        throw new AccessDeniedException("Bad Credentials");
    }

    @PostMapping("/register")
    public void register( @RequestBody UserDto userDto) {
        if (userService.isUsernameExisted(userDto.getUsername())) {
            throw new RuntimeException("Exception.duplicate.username");
        }
        if (userService.isEmailExisted(userDto.getEmail())) {
            throw new RuntimeException("Exception.duplicate.email");
        }
        if (userService.isMobileExisted(userDto.getMobile())) {
            throw new RuntimeException("Exception.duplicate.mobile");
        }
        val user = User.builder()
                .username(userDto.getUsername())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .mobile(userDto.getMobile())
                .password(userDto.getPassword())
                .build();
        userService.register(user);
    }
}
