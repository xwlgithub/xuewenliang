package com.itxwl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/5/27 10:27
 * @Description 描述信息
 */
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
}
