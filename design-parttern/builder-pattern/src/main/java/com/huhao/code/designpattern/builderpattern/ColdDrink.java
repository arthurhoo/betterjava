package com.huhao.code.designpattern.builderpattern;

/**
 * @author: huhao
 * @create: 2024/4/15
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}