package com.itxwl.config;

import com.itxwl.aspect.RoleHierarchyReloadAspect;
import com.itxwl.service.RoleHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

/**
 * @author xueWenLiang
 * @date 2021/6/17 15:07
 * @Description 描述信息
 */
@RequiredArgsConstructor
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {

    private final RoleHierarchyImpl roleHierarchy;
    private final RoleHierarchyService roleHierarchyService;

    @Bean
    public RoleHierarchyReloadAspect roleHierarchyReloadAspect() {
        return new RoleHierarchyReloadAspect(roleHierarchy, roleHierarchyService);
    }
}
