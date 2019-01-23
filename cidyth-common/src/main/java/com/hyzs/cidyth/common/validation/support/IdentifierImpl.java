package com.hyzs.cidyth.common.validation.support;

import com.hyzs.cidyth.common.validation.annotation.Identifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by acer on 2017-07-03.
 */
public class IdentifierImpl implements ConstraintValidator<Identifier,String>{
    private static final Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");

    private boolean required ;

    @Override
    public void initialize(Identifier constraintAnnotation) {
        this.required=constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value || "".equals(value.trim()) ) {
            return !required;
        }

        return !pattern.matcher(value).find();
    }
}
