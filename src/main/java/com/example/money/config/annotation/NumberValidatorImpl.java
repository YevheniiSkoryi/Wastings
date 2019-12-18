package com.example.money.config.annotation;

import com.example.money.config.ErrorException;
import com.example.money.config.ErrorType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidatorImpl implements ConstraintValidator<NumberValidator, String> {
    @Override
    public void initialize(NumberValidator constraintAnnotation) {

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
            throw new ErrorException(
                    "Amount must contain only numbers",
                    ErrorType.LONG_PARSE
            );
        }
        return result;
    }
}
