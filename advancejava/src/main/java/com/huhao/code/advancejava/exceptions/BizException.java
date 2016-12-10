package com.huhao.code.advancejava.exceptions;

/**
 * Created by huhao on 2016/12/9.
 */
public class BizException extends Exception {
    public BizException(){
        super();
    }

    public BizException(String message){
        super(message);
    }



    @Override
    public Throwable fillInStackTrace(){
        return this;
    }
}
