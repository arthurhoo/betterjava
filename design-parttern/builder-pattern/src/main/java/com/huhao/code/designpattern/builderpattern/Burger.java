package com.huhao.code.designpattern.builderpattern;

/**
 * @author: huhao
 * @create: 2024/4/15
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}