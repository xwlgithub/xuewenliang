package com.itxwl.userdetail;

import com.itxwl.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/5/27 13:51
 * @Description 用户名校验
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl  implements UserDetailsService {
    private final UserRepo userRepo;

    /**
     * 根据用户名读取用户信息 返回封装用户体
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //判断是否为空 为空即抛出异常
        return  userRepo.findOptionalByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("未找到用户名"+username));
    }
}
