package com.itxwl.security.auth.ldap;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DataLdapTest
/**
 * @author xueWenLiang
 * @date 2021/6/1 16:20
 * @Description 描述信息
 */
public class LDAPUserRepoIntTests {
    @Autowired
    private LDAPUserRepo ldapUserRepo;

    @BeforeEach
    public void setup() {
    }

//    @Test
//    public void givenUsername_ThenFindUserSuccess() {
//        val user = ldapUserRepo.findByUsername("zhaoliu");
//        assertTrue(user.isPresent());
//    }

    @Test
    public void givenUsernameAndPassword_ThenFindUserSuccess() {
        val user = ldapUserRepo.findByUsernameAndPassword("zhaoliu", "123");
        assertTrue(user.isPresent());
    }

    @Test
    public void givenUsernameAndWrongPassword_ThenFindUserFail() {
        val user = ldapUserRepo.findByUsernameAndPassword("zhaoliu", "bad_password");
        System.out.println(user.toString()+"!!!!!!!!!!!");
        //assertTrue(true,user.get().getUsername());
    }
}
