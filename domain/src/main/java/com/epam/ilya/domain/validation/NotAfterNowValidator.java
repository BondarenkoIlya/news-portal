package com.epam.ilya.domain.validation;

import com.epam.ilya.domain.validation.annotation.NotAfterNow;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotAfterNowValidator implements ConstraintValidator<NotAfterNow, LocalDate> {
    public void initialize(NotAfterNow constraint) {
    }

    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return !date.isAfter(LocalDate.now());//обрабатывать налпоинтер?
    }
}
