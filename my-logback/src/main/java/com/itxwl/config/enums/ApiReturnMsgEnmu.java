package com.itxwl.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author xueWenLiang
 * @date 2021/5/12 14:08
 * @Description 异常描述枚举类
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public enum ApiReturnMsgEnmu {

    THROW_ERROR_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务器异常"),
    LEVEL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"计算异常"),
    SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"保存用户失败");

    private  int code;
    private  String msg;


}
