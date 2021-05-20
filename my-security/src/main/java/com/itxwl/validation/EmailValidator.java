package com.itxwl.validation;

import com.itxwl.annotaion.ValidEmail;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author xueWenLiang
 * @date 2021/5/19 14:16
 * @Description 邮箱自定义注解实现规则控制器
 */
public class EmailValidator  implements ConstraintValidator<ValidEmail,String> {

    private final String EMAIL_PATTERN="^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return validateEmail(value);
    }

    /**
     * 业务判断是否满足正则规则
     * @param email
     * @return
     */
    private boolean validateEmail(final  String email){
        Pattern compile = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = compile.matcher(email);
        return matcher.matches();
    }
}
