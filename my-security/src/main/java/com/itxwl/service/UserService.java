package com.itxwl.service;

import com.itxwl.domain.Auth;
import com.itxwl.domain.Role;
import com.itxwl.domain.User;
import com.itxwl.repository.RoleRepo;
import com.itxwl.repository.UserRepo;
import com.itxwl.util.JwtUtil;
import com.itxwl.util.TotpUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.itxwl.util.Constants.ROLE_USER;

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
    private final RoleRepo roleRepo;
    private final TotpUtil totpUtil;

    /**
     * 登录获取token
     *
     * @param username\
     * @param password
     * @return
     */
    public Auth login(String username, String password) {
        return userRepo.findOptionalByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> new Auth(
                        jwtUtil.createAccessToken(user), jwtUtil.createRefreshToken(user)
                )).orElseThrow(() -> new BadCredentialsException("用户名密码错误"));

    }

    public Auth login(UserDetails userDetails) {
        return new Auth(jwtUtil.createAccessToken(userDetails), jwtUtil.createRefreshToken(userDetails));
    }

    /**
     * 注册一个新用户
     *
     * @param user 用户实体
     * @return 保存后的对象
     */
    @Transactional
    public User register(User user) {
        Optional<Role> role1 = roleRepo.findOptionalByAuthority(ROLE_USER);
        Role role2 = role1.get();
        Set<Role> roles = new HashSet<>();
        roles.add(role2);
        user.setAuthorities(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.withMfakey(totpUtil.encodeKeyToString());
        userRepo.save(user);
        return user;
    }

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 存在与否
     */
    public boolean isUsernameExisted(String username) {
        return userRepo.countByUsername(username) > 0;
    }

    /**
     * 判断电邮地址是否存在
     *
     * @param email 电邮地址
     * @return 存在与否
     */
    public boolean isEmailExisted(String email) {
        return userRepo.countByEmail(email) > 0;
    }

    /**
     * 判断手机号是否存在
     *
     * @param mobile 手机号
     * @return 存在与否
     */
    public boolean isMobileExisted(String mobile) {
        return userRepo.countByMobile(mobile) > 0;
    }


    public Optional<User> findOptionalByUsernameAndPassword(String username, String password) {
        return userRepo.findOptionalByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        return userRepo.findOptionalByUsername(userDetails.getUsername())
                .map(user ->
                        (UserDetails) userRepo.save(user.withPassword(newPassword))
                ).orElse(userDetails);
    }

    public Optional<String> createTotp(User user) {
        return totpUtil.createTotp(user.getMfakey());
    }

    public Optional<User> findOptionalByUsername(String username) {
        return userRepo.findOptionalByUsername(username);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public Auth loginWithTotp(User user) {
        val toSave = user.withMfakey(totpUtil.encodeKeyToString());
        val saved = saveUser(toSave);
        return login(saved);
    }
    public  boolean isAuthEqualName(Authentication authentication,String userName){
        return authentication.getName().equals(userName);
    }

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    public Optional<User> findOptionalByEmail(String email){
        return userRepo.findOptionByEmail(email);
    }
}
