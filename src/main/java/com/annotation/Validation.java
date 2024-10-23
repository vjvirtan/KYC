package com.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Validation {

}
