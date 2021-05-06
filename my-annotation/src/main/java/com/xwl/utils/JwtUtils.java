package com.xwl.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.xwl.domain.XwlUser;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xueWenLiang
 * @date 2021/4/30 10:21
 * @Description 描述信息
 * iss: jwt签发者
 *
 * sub: jwt所面向的用户
 *
 * aud: 接收jwt的一方
 *
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 *
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 *
 * iat: jwt的签发时间
 *
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
 */
//@Component
public class JwtUtils {
    private static final String SIGN_NAME = "xuewenliang";
    private static final String HEADER_TOKEN_KEY = "xwl_token";
    private static long mill = 60000L;

    /**
     * 生成token
     *
     * @param name
     * @param emailAdress
     * @return
     */
    public static String createTokenByParams(String name, String emailAdress) {
        //当前时间-毫秒
        long now = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder().setId(name)
                .setSubject(emailAdress)
                .signWith(SignatureAlgorithm.HS256, SIGN_NAME)
                .setIssuedAt(new Date());
        String compact = builder.compact();
        return compact ;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parseClaimsByToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SIGN_NAME).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("token异常");
        } catch (UnsupportedJwtException e) {
            System.out.println("token异常");
        } catch (MalformedJwtException e) {
            System.out.println("token异常");
        } catch (SignatureException e) {
            System.out.println("token异常");
        } catch (IllegalArgumentException e) {
            System.out.println("无权限");
        }
        return claims;
    }

    /**
     * 根据自定义注解注入分析请求头token封装用户信息返回
     * @param request
     * @return
     */
    public static XwlUser getUserParameters(HttpServletRequest request) {
        String tokenValue = request.getHeader(HEADER_TOKEN_KEY);
        //这里不用判断是否为空
        // TODO 备注:因为是否携带token由其它层负责拦截,用户信息获取token解析已经进入业务层
        //解析token串获取信息
        Claims claims = parseClaimsByToken(tokenValue);
        XwlUser xwlUser = new XwlUser();
        //设置名称
        xwlUser.setUserName(claims.getId());
        //设置地址
        xwlUser.setUserAddress(claims.getSubject());
        return xwlUser;
    }
}
