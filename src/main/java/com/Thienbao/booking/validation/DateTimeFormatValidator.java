package com.Thienbao.booking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormatValidator  implements ConstraintValidator<ValidDateTimeFormat,String>{
    private String pattern;

    @Override
    public void initialize(ValidDateTimeFormat constraintAnnotation) {
       this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String dateTimeStr, ConstraintValidatorContext context) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime.parse(dateTimeStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
