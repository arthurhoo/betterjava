package com.huhao.code.designpattern.builderpattern;

/**
 * @author: huhao
 * @create: 2024/4/15
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
