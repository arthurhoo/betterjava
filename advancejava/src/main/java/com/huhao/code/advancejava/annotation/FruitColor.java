package com.huhao.code.advancejava.annotation;

import java.lang.annotation.*;

/**
 * Created by huhao on 16/7/27.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    public enum Color{BULUE, RED, GREEN}

    Color fruitColor() default Color.GREEN;
}
