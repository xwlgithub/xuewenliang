package com.itxwl.domain.dto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/6/2 10:41
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
