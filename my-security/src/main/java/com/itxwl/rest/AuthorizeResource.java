package com.itxwl.rest;

import com.itxwl.domain.Auth;
import com.itxwl.domain.MfaType;
import com.itxwl.domain.User;
import com.itxwl.domain.dto.LoginDto;
import com.itxwl.domain.dto.SendTotpDto;
import com.itxwl.domain.dto.TotpVerificationDto;
import com.itxwl.domain.dto.UserDto;
import com.itxwl.exception.InvalidTotpProblem;
import com.itxwl.exception.UserAccountExpiredProblem;
import com.itxwl.exception.UserAccountLockedProblem;
import com.itxwl.exception.UserNotEnabledProblem;
import com.itxwl.service.EmailService;
import com.itxwl.service.SmsService;
import com.itxwl.service.UserCacheService;
import com.itxwl.service.UserService;
import com.itxwl.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:07
 * @Description 描述信息
 */
@RestController
@RequestMapping("/authorize")
@RequiredArgsConstructor
@Slf4j
public class AuthorizeResource {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserCacheService userCacheService;
    private final SmsService smsService;
    private final EmailService emailService;


    /**
     * 获取token
     *
     * @param loginDto
     * @return
     */
//    @PostMapping("/token")
//    public Auth login(@Valid @RequestBody LoginDto loginDto) {
//        return userService.login(loginDto.getUsername(),loginDto.getPassword());
//    }
    @PostMapping("/token")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return userService.findOptionalByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword())
                .map(user -> {
                    //升级密码编码
                    userService.updatePassword(user, loginDto.getPassword());
                    //验证
                    if (!user.isEnabled()) {
                        throw new UserNotEnabledProblem();
                    }
                    if (!user.isAccountNonExpired()) {
                        throw new UserAccountExpiredProblem();
                    }
                    if (!user.isAccountNonLocked()) {
                        throw new UserAccountLockedProblem();
                    }
                    //判断usingMfa 如果是false  返回token
                    if (user.isUsingMfa()) {
                        return ResponseEntity.ok().body(userService.login(loginDto.getUsername(), loginDto.getPassword()));
                    }
                    //如果使用多因子认证
                    val mfaId = userCacheService.cacheUser(user);
                    return ResponseEntity
                            .status(HttpStatus.UNAUTHORIZED)
                            .header("X-Authenticate", "mfa", "realm=" + mfaId)
                            .build();
                }).orElseThrow(() -> new BadCredentialsException("用户名密码错误"));
    }

    /**
     * 刷新token
     *
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

    @PutMapping("/totp")
    public void sendTotp(@Valid @RequestBody SendTotpDto sendTotpDto) {
        Optional<Pair<User, String>> pair = userCacheService.retrieveUser(sendTotpDto.getMfaId())
                .flatMap(user -> userService.createTotp(user).map(code -> Pair.of(user, code)));
        Pair<User, String> userStringPair = pair.get();
        String mobile = userStringPair.getFirst().getMobile();
        log.debug("totp: {}", userStringPair.getSecond());
        if (sendTotpDto.getMfaType() == MfaType.SMS) {
            smsService.send(mobile, userStringPair.getSecond());
        } else {
            emailService.send(userStringPair.getFirst().getEmail(), userStringPair.getSecond());
        }
    }
    @PostMapping("/totp")
    public Auth verifyTotp(@Valid @RequestBody TotpVerificationDto totpVerificationDto) {
        return userCacheService.verifyTotp(totpVerificationDto.getMfaId(), totpVerificationDto.getCode())
                .map(User::getUsername)
                .flatMap(userService::findOptionalByUsername)
                .map(userService::loginWithTotp)
                .orElseThrow(InvalidTotpProblem::new);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
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
