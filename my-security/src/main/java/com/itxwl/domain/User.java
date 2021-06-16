package com.itxwl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author xueWenLiang
 * @date 2021/5/27 10:18
 * @Description 描述信息
 */
@SuppressWarnings("all")
@Data
@Entity
@Table(name = "mooc_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder //
@With
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,unique = true,nullable = false)
    private String username;
    @Column(name = "password_hash",length = 80,nullable = false)
    @JsonIgnore
    private String password;
    @Column(length = 255,unique = true,nullable = false)
    private String email;
    @Column(length = 12,unique = true,nullable = false)
    private String mobile;
    @Column(length = 50)
    private String name;
    @Column(nullable = false)
    private boolean enabled;
    @Column(name = "account_non_expired",nullable = false)
    private boolean accountNonExpired;
    @Column(name = "account_non_locked",nullable = false)
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired",nullable = false)
    private boolean credentialsNonExpired;
    /**
     * 是否启用两部认证
     */
    @Builder.Default
    @Column(name = "using_mfa",nullable = false)
    private boolean usingMfa=false;
    /**
     * 两部认证key
     */
    @JsonIgnore
    @Column(name = "mfa_key",nullable = false)
    private String mfakey;
    /**
     * 多对多关联表-》mooc_user_roles
     */
    @ManyToMany
    //获取用户的同时将角色获取到-提高性能
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "mooc_users_roles",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private Set<Role> authorities;
    //role集合与 getAuthorities()完全匹配
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
}
