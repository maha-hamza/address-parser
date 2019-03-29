package com.address.parser.address_parser.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullCheckValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullCheckConstraint {

    String message() default "Address Input Can't be Null Or Empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
