package com.itxwl.service;

import com.itxwl.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xueWenLiang
 * @date 2021/6/17 13:58
 * @Description 描述信息
 */
@RequiredArgsConstructor
@Service
public class RoleHierarchyService {
    private final RoleRepo roleRepo;

//    public boolean isRoleNameExisted(String roleName) {
//        return roleRepo.countByRoleNameIgnoreCase(roleName) > 0;
//    }
//
//    public boolean isRoleNameExistedAndIdIsNot(String roleName, Long id) {
//        return roleRepo.countByRoleNameIgnoreCaseAndIdNot(roleName, id) > 0;
//    }
//
//    public boolean isRoleAssigned(Long id) {
//        return roleRepo.countByAssigned(id) > 0;
//    }
}
