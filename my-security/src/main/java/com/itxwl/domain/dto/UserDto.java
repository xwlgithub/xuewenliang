package com.itxwl.domain.dto;

import com.itxwl.annotaion.PasswordMatch;
import com.itxwl.annotaion.ValidEmail;
import com.itxwl.annotaion.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:02
 * @Description 描述信息
 */
@Data
@PasswordMatch
public class UserDto implements Serializable {

    @NotNull
    @NotBlank
    @Size(min = 4,max = 50,message = "用户名长度必须在4-50字符之间")
    private String username;
   @NotNull
//    @NotBlank
//    @Size(min = 8,max = 20,message = "密码长度必须在8-20字符之间")
    @ValidPassword
    private String password;
//    @NotBlank
//    @Size(min = 8,max = 20,message = "密码长度必须在8-20字符之间")
    private String matchPassword;
    @NotNull
    @NotBlank
    @ValidEmail
    private String email;
    @NotNull
//    @NotBlank
//    @Size(min = 8,max = 20,message = "密码长度必须在8-20字符之间")
    private String mobile;
    @NotNull
    @NotBlank
    @Size(min = 4,max = 20,message = "姓名长度必须在4-20字符之间")
    private String name;


}
