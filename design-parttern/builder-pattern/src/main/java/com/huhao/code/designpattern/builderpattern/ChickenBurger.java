package com.huhao.code.designpattern.builderpattern;

/**
 * @author: huhao
 * @create: 2024/4/15
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}