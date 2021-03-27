package com.xwl.annotation;
import java.lang.annotation.*;
@Target({ElementType.TYPE,ElementType.METHOD})//可以在类上使用 也可以在方法上使用
@Retention(RetentionPolicy.RUNTIME)//运行时可通过反射获取 载体
@Documented//载体
public @interface XwlRequestMapping {
    String value() default  "";
}

