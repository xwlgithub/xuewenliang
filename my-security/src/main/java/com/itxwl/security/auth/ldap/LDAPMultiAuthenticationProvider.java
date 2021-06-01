package com.itxwl.security.auth.ldap;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author xueWenLiang
 * @date 2021/6/1 16:28
 * @Description 描述信息
 */
@RequiredArgsConstructor
public class LDAPMultiAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final LDAPUserRepo ldapUserRepo;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return ldapUserRepo.findByUsernameAndPassword(username, authentication.getCredentials().toString())
                .orElseThrow(() -> new BadCredentialsException("[LDAP] 用户名或密码错误"));
    }

}
