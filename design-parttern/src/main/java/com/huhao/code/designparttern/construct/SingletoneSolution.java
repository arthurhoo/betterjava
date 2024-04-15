package com.huhao.code.designparttern.construct;

/**
 * 单例模式---内部静态类
 * @author: huhao
 * @create: 2024/4/15
 */
public class SingletoneSolution {

    private static class SingletonHolder{

        private static final SingletoneSolution INSTANCE = new SingletoneSolution();
    }

    private SingletoneSolution(){

    }

    public static SingletoneSolution getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
