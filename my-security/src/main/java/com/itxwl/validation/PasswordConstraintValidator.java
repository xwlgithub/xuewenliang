package com.itxwl.validation;

import com.itxwl.annotaion.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xueWenLiang
 * @date 2021/5/19 14:16
 * @Description 邮箱自定义注解实现规则控制器
 */
@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
    private final SpringMessageResolver springMessageResolver;
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(springMessageResolver,Arrays.asList(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(String.join(",",validator.getMessages(result)))
                .addConstraintViolation();
        return result.isValid();
    }

}
