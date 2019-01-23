package com.hyzs.cidyth.common.validation.annotation;

import com.hyzs.cidyth.common.validation.support.IdentifierImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 编号，只允许出现字母和数字
 * Created by acer on 2017-07-03.
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { IdentifierImpl.class })
public @interface Identifier {

    public boolean required() default true; //是否必须

    public String message() default "{identifier}{illegal}";

    public Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
