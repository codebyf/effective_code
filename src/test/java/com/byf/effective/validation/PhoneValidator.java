package com.byf.effective.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义手机号约束注解关联验证器
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    /**
     * 自定义验证逻辑
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //手机号验证规则：187开头，后边随便
        String check = "187\\d{8}";
        Pattern regex = Pattern.compile(check);
        String phone = Optional.ofNullable(s).orElse("");
        Matcher matcher = regex.matcher(phone);
        return matcher.matches();
    }
}
