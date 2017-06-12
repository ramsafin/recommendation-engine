package ru.kpfu.itis.javaLab.web.validators.annotations;

import ru.kpfu.itis.javaLab.web.validators.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ImageValidator.class)
@Documented
public @interface ImageValid {

    String message() default "Image type is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}