package com.itxwl.util;

import com.itxwl.config.AppProperties;
import com.itxwl.domain.Role;
import com.itxwl.domain.User;
import io.jsonwebtoken.Jwts;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xueWenLiang
 * @date 2021/6/1 16:56
 * @Description 描述信息
 */
@ExtendWith(SpringExtension.class)
public class JwtUtilTest {
    private JwtUtil jwtUtil;
    @BeforeEach
    public void setup(){
        jwtUtil=new JwtUtil(new AppProperties());
    }
    @Test
    public void givenUserDetails_thenCreateTokenSuccess(){
        val username="user";
        Set set=new HashSet();
        Role role_user = Role.builder()
                .authority("ROLE_USER")
                .build();
        Role role_admin = Role.builder().authority("ROLE_ADMIN").build();
        set.add(role_user);
        set.add(role_admin);
        User build = User.builder().username(username).authorities(set).build();
        val token=jwtUtil.createAccessToken(build);
        val parsedClaims= Jwts.parserBuilder()
                .setSigningKey(jwtUtil.key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        assertEquals(username,parsedClaims.getSubject(),"解析后 Subject应该是用户名");
    }
}
