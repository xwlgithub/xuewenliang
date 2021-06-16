package com.itxwl.repository;

import com.itxwl.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author xueWenLiang
 * @date 2021/5/27 13:52
 * @Description 描述信息
 */
@Repository
public interface UserRepo  extends JpaRepository<User,Long> {
Optional<User> findOptionalByUsername(String username);
    long countByUsername(String username);

    long countByEmail(String email);

    long countByMobile(String mobile);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    Optional<User> findOptionByEmail(String email);
}
