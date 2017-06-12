package ru.kpfu.itis.javaLab.web.validators;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.javaLab.web.forms.PostForm;
import ru.kpfu.itis.javaLab.web.validators.annotations.ImageValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */
public class ImageValidator implements ConstraintValidator<ImageValid, PostForm> {

    @Override
    public void initialize(ImageValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(PostForm value, ConstraintValidatorContext context) {

        MultipartFile picture = value.getPicture();

        // accept no picture
        if (picture.isEmpty()) return true;

        String contentType = picture.getContentType();

        String[] mime = contentType.split("/");

        if ("image".equals(mime[0]) && Arrays.asList("jpg", "jpeg", "png").contains(mime[1])) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
            .addPropertyNode("picture").addConstraintViolation();

        return false;
    }
}
