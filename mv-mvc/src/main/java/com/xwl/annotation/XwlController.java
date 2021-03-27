package com.xwl.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//只能在类上使用
@Retention(RetentionPolicy.RUNTIME)//运行可通过反射获取 载体
@Documented //载体
public @interface XwlController {
    String value() default  "";
}
