package com.itxwl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/6/2 10:40
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth implements Serializable {
    private String accessToken;
    private String refreshToken;
}
