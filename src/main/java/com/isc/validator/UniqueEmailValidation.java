package com.isc.validator;

import com.isc.repository.AccountRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidation implements ConstraintValidator<UniqueEmailValidator, String> {

    @Autowired
    AccountRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return repository.findAllByEmail(value).size() == 0;
    }
}
