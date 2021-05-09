package com.jm.dynamicDataSource.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiDataSource {

    String name() default "";

}

