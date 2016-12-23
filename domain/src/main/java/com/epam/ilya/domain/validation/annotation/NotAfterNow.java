package com.epam.ilya.domain.validation.annotation;

import com.epam.ilya.domain.validation.NotAfterNowValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotAfterNowValidator.class)
@Documented
public @interface NotAfterNow {

    String message() default "{err.news.date.after}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
