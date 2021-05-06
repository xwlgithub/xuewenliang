package com.xwl.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xueWenLiang
 * @date 2021/4/30 14:23
 * @Description 描述信息
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public enum XwlResultErrorEnum {
    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    NOT_FOUND(404, "服务或资源未找到"),
    ERROR(500, "服务异常"),
    TOKEN_EXPIRE(401, "token过期，请重新登录！"),
    FORBIDDEN(403, "访问被拒绝，您无权访问！"),
    USER_NOT_NULL(404, "该用户不存在");
    //异常编号
    private Integer code;
    //异常注释
    private String msg;
}
