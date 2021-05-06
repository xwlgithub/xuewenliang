package com.xwl.annotation;

import java.lang.annotation.*;

/**
 * @author xueWenLiang
 * @date 2021/4/29 17:30
 * @Description 描述信息
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestUser {
    /**
     * 是否查询LoginUser对象所有信息，true则通过rpc接口查询
     */
    boolean value() default false;
}
