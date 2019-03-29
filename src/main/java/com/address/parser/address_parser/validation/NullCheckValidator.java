package com.address.parser.address_parser.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NullCheckValidator implements
        ConstraintValidator<
                NullCheckConstraint, String> {
    @Override
    public boolean isValid(String address, ConstraintValidatorContext context) {
        if (address == null)
            return false;
        else if (Objects.requireNonNull(address).isBlank())
            return false;
        return true;
    }

}
