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
     *
     * @param userDetails
     * @return
     */
    public String createAccessToken(UserDetails userDetails) {
        return createJWTToken(userDetails, properties.getJwt().getAccessTokenExpireTime(), key);
    }

    /**
     * 根据用户信息封装token(RefreshToken)
     *
     * @param userDetails
     * @return
     */
    public String createRefreshToken(UserDetails userDetails) {
        return createJWTToken(userDetails, properties.getJwt().getRefreshTokenExpireTim(), refreshKey);
    }

    public boolean validateAccessTokenWithoutExpiration(String jwtToken) {
        return validateToken(jwtToken, key);
    }

    public boolean validateAccessToken(String jwtToken) {
        return validateToken(jwtToken, key);
    }

    public boolean validateRefreshToken(String jwtToken) {
        return validateToken(jwtToken, refreshKey);
    }

    public boolean validateToken(String jwtToken, Key signKey) {
        try {
            Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String buildAccessTokenWithRefreshToken(String jwtToken) {
        return parseClaims(jwtToken, refreshKey)
                .map(claims -> Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + properties.getJwt().getAccessTokenExpireTime()))
                        .signWith(key, SignatureAlgorithm.HS512).compact())
                .orElseThrow(() -> new RuntimeException("访问受限"));
    }

    public Optional<Claims> parseClaims(String jwtToken, Key signKey) {
        try {
            val claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(jwtToken).getBody();
            return Optional.of(claims);
        } catch (Exception e) {
            return Optional.empty();
        }
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
