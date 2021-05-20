package com.itxwl.annotaion;

import com.itxwl.validation.EmailValidator;
import com.itxwl.validation.PasswordMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author xueWenLiang
 * @date 2021/5/20 10:25
 * @Description 描述信息
 */
@Retention(RetentionPolicy.RUNTIME)
//　　@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
//　　@Retention(RetentionPolicy.CLASS)   // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
//　　@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Constraint(validatedBy = PasswordMatchValidator.class)
//说明该注解将被包含在javadoc中
@Documented
public @interface PasswordMatch {

    String message() default  "Invalid Password not Match";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
