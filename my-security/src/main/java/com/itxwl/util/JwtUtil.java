package com.itxwl.util;

import com.itxwl.config.AppProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xueWenLiang
 * @date 2021/6/1 16:50
 * @Description 描述信息
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {
    //签名key
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // 用于签名 Access Token
    public static final Key refreshKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // 用于签名 Refresh Token
    private final AppProperties properties;

    /**
     * 根据用户信息封装token
     * @param userDetails
     * @return
     */
    public String createAccessToken(UserDetails userDetails) {
        return createJWTToken(userDetails, properties.getJwt().getAccessTokenExpireTime(), key);
    }
    /**
     * 根据用户信息封装token(RefreshToken)
     * @param userDetails
     * @return
     */
    public String createRefreshToken(UserDetails userDetails) {
        return createJWTToken(userDetails, properties.getJwt().getRefreshTokenExpireTim(), refreshKey);
    }

    /**
     * 根据用户信息生成一个 加密token令牌
     *
     * @param userDetails 用户信息
     * @param
     * @param signKey     签名使用的 key
     * @return JWT
     */
    public String createJWTToken(UserDetails userDetails, long timeToExpire, Key signKey) {
        return Jwts
                .builder()
                .setId("imooc")
                .setSubject(userDetails.getUsername())
                .claim("authorities",
                        userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeToExpire))
                .signWith(signKey, SignatureAlgorithm.HS512).compact();
    }
}
