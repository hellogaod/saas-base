package com.base.saas.common.start.cache;

import java.lang.annotation.*;

/**
 * Created by zh-pc on 2017/3/10.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLoaderAnno {

    String description() default "";

}

