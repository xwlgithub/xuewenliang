package com.itxwl.annotaion;

import com.itxwl.validation.EmailValidator;
import com.itxwl.validation.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:17
 * @Description 描述信息
 */

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
//　@Target(ElementType.TYPE)   //接口、类、枚举、注解
//　　　　@Target(ElementType.FIELD) //字段、枚举的常量
//　　　　@Target(ElementType.METHOD) //方法
//　　　　@Target(ElementType.PARAMETER) //方法参数
//　　　　@Target(ElementType.CONSTRUCTOR)  //构造函数
//　　　　@Target(ElementType.LOCAL_VARIABLE)//局部变量
//　　　　@Target(ElementType.ANNOTATION_TYPE)//注解
//　　　　@Target(ElementType.PACKAGE) ///包
@Retention(RetentionPolicy.RUNTIME)
//　　@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
//　　@Retention(RetentionPolicy.CLASS)   // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
//　　@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Constraint(validatedBy = PasswordConstraintValidator.class)
//说明该注解将被包含在javadoc中
@Documented
public @interface ValidPassword {

    String message() default  "Invalid Password";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
