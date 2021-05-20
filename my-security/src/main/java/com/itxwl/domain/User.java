package com.itxwl.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:00
 * @Description 描述信息
 */
@Data
public class User implements Serializable {

    private String username;
    private String password;
    private String email;
    private String name;


}
