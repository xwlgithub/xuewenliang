package com.itxwl.security.auth.ldap;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author xueWenLiang
 * @date 2021/6/1 16:18
 * @Description 描述信息
 */
@Repository
public interface LDAPUserRepo extends LdapRepository<LDAPUser> {
    Optional<LDAPUser> findByUsernameAndPassword(String username, String password);

}
