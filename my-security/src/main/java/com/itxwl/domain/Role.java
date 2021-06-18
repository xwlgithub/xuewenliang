package com.itxwl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xueWenLiang
 * @date 2021/5/27 10:27
 * @Description 描述信息
 */
@Builder//可以对当前对象进行builder封装
@SuppressWarnings("all")
@Data
@Entity
@Table(name = "mooc_roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name",unique = true,length = 50,nullable = false)
    private String authority;
    /**
     * 角色名称，有唯一约束，不能重复
     */
    @NotNull
   // @Pattern(regexp = Constants.PATTERN_ROLE_NAME)
    @Column(name = "role_name", unique = true, nullable = false, length = 50)
    private String roleName;
    /**
     * 角色对应权限集合
     */
    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(
            name = "mooc_roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions = new HashSet<>();
}
