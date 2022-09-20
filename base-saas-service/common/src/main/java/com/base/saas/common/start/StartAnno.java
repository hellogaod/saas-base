package com.base.saas.common.start;

import java.lang.annotation.*;

/**
 * Created by zh-pc on 2017/3/10.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StartAnno {

    String description() default "";

    int priority() default 0;
}
