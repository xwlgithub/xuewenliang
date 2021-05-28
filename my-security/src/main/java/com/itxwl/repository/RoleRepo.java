package com.itxwl.repository;

import com.itxwl.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xueWenLiang
 * @date 2021/5/27 13:55
 * @Description 描述信息
 */
@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
}
