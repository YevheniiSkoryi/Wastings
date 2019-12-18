package com.example.money.config.annotation;

import com.example.money.config.ErrorException;
import com.example.money.config.ErrorType;
import com.example.money.dto.binding.JsonDateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateTimeValidatorImpl implements ConstraintValidator<DateTimeValidator, String> {


    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (date != null) {
                LocalDateTime time = LocalDateTime.parse(date, JsonDateTimeFormatter.formatter);
            }
        } catch (ConstraintViolationException | DateTimeParseException exp) {
            throw new ErrorException(
                    date + " is incorrect date time format, must be yyyy-MM-dd HH:mm",
                    ErrorType.DATE_PARSE
            );
        }
        return true;
    }
}
