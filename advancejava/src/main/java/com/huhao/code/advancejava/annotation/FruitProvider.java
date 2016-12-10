package com.huhao.code.advancejava.annotation;

import java.lang.annotation.*;

/**
 * Created by huhao on 16/7/27.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    public int id() default -1;

    public String name() default "";

    public String address() default "";
}
