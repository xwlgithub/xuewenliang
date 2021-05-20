package com.itxwl.validation;

import com.itxwl.annotaion.PasswordMatch;
import com.itxwl.annotaion.ValidPassword;
import com.itxwl.domain.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author xueWenLiang
 * @date 2021/5/20 10:24
 * @Description 描述信息
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserDto> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        boolean isEquals = userDto.getPassword().equals(userDto.getMatchPassword());

        return isEquals;
    }
}
