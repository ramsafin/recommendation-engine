package ru.kpfu.itis.javaLab.web.validators;

import ru.kpfu.itis.javaLab.web.forms.RegistrationForm;
import ru.kpfu.itis.javaLab.web.validators.annotations.PasswordsMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Safin Ramil on 09.06.17
 * RamilSafNab1996@gmail.com
 */

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, RegistrationForm> {


    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(RegistrationForm value, ConstraintValidatorContext context) {
        return value.getPassword() != null && value.getPassword().equalsIgnoreCase(value.getPasswordRepeat());
    }
}