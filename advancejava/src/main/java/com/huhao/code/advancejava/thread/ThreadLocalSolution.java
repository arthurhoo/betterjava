package com.huhao.code.advancejava.thread;

/**
 * @author: Arthur Hu
 * @date: 2018/8/7 下午4:13
 * Description:
 */
public class ThreadLocalSolution {



    class ThreadLocalWithUserContext implements Runnable{

        private  final ThreadLocal<Integer> userContext = new ThreadLocal<Integer>();


        public void run() {

        }
    }
}
