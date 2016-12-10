package com.huhao.code.advancejava.guava;

/**
 * Created by huhao on 2016/12/10.
 */
public class RateLimiterJob implements Runnable {

    private String threadName;

    public RateLimiterJob(String threadName){
        this.threadName = threadName;
    }

    public void run() {
        System.out.println(threadName+"is running");
    }
}
