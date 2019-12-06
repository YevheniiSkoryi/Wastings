package com.example.money.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoubleValidator implements ConstraintValidator<DoubleParser, String> {
    @Override
    public void initialize(DoubleParser constraintAnnotation) {

    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        try {
            if (number != null) {
                Long num = Long.parseLong(number);
            } else {
                result = false;
            }
        } catch (NumberFormatException exp) {
            result = false;
        }
        return result;
    }
}
