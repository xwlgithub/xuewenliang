package com.xwl.utils;

import com.xwl.config.enums.XwlResultErrorEnum;
import lombok.Data;

/**
 * @author xueWenLiang
 * @date 2021/4/29 16:31
 * @Description 描述信息
 */
@Data
public class R<T> {


    private Integer code;
    private String message;
    private T data;

    /**
     * 无参成功返回体
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> OK() {
        return result(null, XwlResultErrorEnum.SUCCESS.getCode(),  XwlResultErrorEnum.SUCCESS.getMsg());
    }

    /**
     * 有数据返回体
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> OK(T data) {
        return result(data, XwlResultErrorEnum.SUCCESS.getCode(),  XwlResultErrorEnum.SUCCESS.getMsg());
    }

    /**
     * 无备注服务器异常
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> serverError() {
        return result(null, XwlResultErrorEnum.ERROR.getCode(),  XwlResultErrorEnum.ERROR.getMsg());
    }

    /**
     * 自定义异常回执信息
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> exceptionReturn(XwlResultErrorEnum xwlResultErrorEnum) {
        return result(null, xwlResultErrorEnum.getCode(), xwlResultErrorEnum.getMsg());
    }

    /**
     * 统一封装异常体
     *
     * @param data
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> result(T data, int code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMessage(message);
        return r;
    }
    
}
