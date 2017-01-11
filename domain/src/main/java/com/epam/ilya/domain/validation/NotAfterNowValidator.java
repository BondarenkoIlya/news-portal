package com.epam.ilya.domain.validation;

import com.epam.ilya.domain.validation.annotation.NotAfterNow;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * {@inheritDoc}
 *
 * @author Ilya_Bondarenko
 */
public class NotAfterNowValidator implements ConstraintValidator<NotAfterNow, LocalDate> {

    /**
     * {@inheritDoc}
     **/
    public void initialize(NotAfterNow constraint) {
    }

    /**
     * {@inheritDoc}
     **/
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return !date.isAfter(LocalDate.now());
    }
}
