package com.xwl.annotation;
import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XwlAutowried {
    String value() default "";
}
