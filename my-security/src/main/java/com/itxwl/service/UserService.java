package com.itxwl.service;

import com.itxwl.domain.Auth;
import com.itxwl.repository.UserRepo;
import com.itxwl.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/6/2 10:43
 * @Description 描述信息
 */
@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepo userRepo;
private final PasswordEncoder passwordEncoder;
private final JwtUtil jwtUtil;

public Auth login(String username,String password){
    return userRepo.findOptionalByUsername(username)
            .filter(user -> passwordEncoder.matches(password,user.getPassword()))
            .map(user -> new Auth(
                    jwtUtil.createAccessToken(user),jwtUtil.createRefreshToken(user)
            )).orElseThrow(() -> new BadCredentialsException("用户名密码错误"));

}

}
