package com.huhao.code.designpattern.builderpattern;

/**
 * @author: huhao
 * @create: 2024/4/15
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
