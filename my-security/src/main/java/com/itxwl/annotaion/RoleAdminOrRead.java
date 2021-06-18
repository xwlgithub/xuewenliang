package com.itxwl.annotaion;

import com.itxwl.util.Constants;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xueWenLiang
 * @date 2021/6/17 14:59
 * @Description 描述信息
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('" +
        Constants.ROLE_ADMIN +
        "', '" +
        Constants.AUTHORITY_USER_READ +
        "')")
public @interface RoleAdminOrRead {
}
