package com.huhao.code.advancejava.performance.exceptionPerf;

import java.io.Serializable;

/**
 * Created by huhao on 16/10/14.
 */
public class NormalObject implements Serializable{

   private  String message;

    public NormalObject(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
