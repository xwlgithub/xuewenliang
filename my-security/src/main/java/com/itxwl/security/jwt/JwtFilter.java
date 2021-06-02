package com.itxwl.security.jwt;

import com.itxwl.config.AppProperties;
import com.itxwl.util.CollectionUtil;
import com.itxwl.util.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xueWenLiang
 * @date 2021/6/2 10:05
 * @Description JWT过滤器
 */
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final AppProperties appProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkJwtToken(request)) {
            //合法请求时 进行业务处理
            Optional<Claims> claims = validateToken(request);
            Optional<Claims> optionalClaims = claims.filter(claim -> claim.get("authorities") != null);
            //有值
            if (!ObjectUtils.isEmpty(optionalClaims)) {
                List<?> rawList = CollectionUtil.convertObjectToList(optionalClaims.get().get("authorities"));
                val authorities = rawList.stream()
                        .map(String::valueOf)
                        .map(strAuthority -> new SimpleGrantedAuthority(strAuthority))
                        .collect(Collectors.toList());
                Authentication authentication = new UsernamePasswordAuthenticationToken(optionalClaims.get().getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                //无值
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 解析token  构建claims对象信息体
     *
     * @param request
     * @return
     */
    private Optional<Claims> validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(appProperties.getJwt().getHeader()).replace(appProperties.getJwt().getPrefix(), "");
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(JwtUtil.key).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwtToken);
            Claims body = claimsJws.getBody();
            return  Optional.of(body);
           // return Optional.of(Jwts.parserBuilder().setSigningKey(JwtUtil.key).build().parseClaimsJws(jwtToken).getBody());
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * 检查JWT Token是否存在http报头中
     *
     * @param request
     * @return
     */
    private boolean checkJwtToken(HttpServletRequest request) {
        //获取请求头
        String authenticationHeader = request.getHeader(appProperties.getJwt().getHeader());
        return authenticationHeader != null && authenticationHeader.startsWith(appProperties.getJwt().getPrefix());
    }
}
