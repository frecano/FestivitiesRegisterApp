package com.festivities_register.beanvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {DateRangeValidator.class})
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeValidation {
	String message() default "{endDate} should be later than {startDate0}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String from();
    String to();
}
