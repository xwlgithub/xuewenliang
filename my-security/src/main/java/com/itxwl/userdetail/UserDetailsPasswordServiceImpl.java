package com.itxwl.userdetail;

import com.itxwl.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/5/27 14:11
 * @Description 描述信息
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsPasswordServiceImpl implements UserDetailsPasswordService {
    private final UserRepo userRepo;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        log.info("进入密码二次hash:{}"+userDetails+newPassword);
        return userRepo.findOptionalByUsername(userDetails.getUsername())
                .map(user ->
                     (UserDetails) userRepo.save(user.withPassword(newPassword))
                ).orElse(userDetails);
    }
}
