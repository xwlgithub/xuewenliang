package com.itxwl.annotation;

import java.lang.annotation.*;

/**
 * @author xueWenLiang
 * @date 2021/5/10 14:10
 * @Description 描述信息
 */
//标注API
@Documented
//配置作用范围
@Target({ElementType.METHOD})
//运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface XwlLog {
     /**
      * 日志描述信息
      * @return
      */
     String describution() default "";



}
