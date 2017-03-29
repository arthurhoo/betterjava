package com.huhao.code.advancejava.algorithm;

/**
 * Created by huhao on 2017/3/26.
 */
public class NumberAlgorithm {

    public boolean checkPerfectNumber(int num) {
        if(num<0 || num > Integer.MAX_VALUE){
            throw new IllegalArgumentException("parameter error");
        }
        int sum = 0;
        for(int i=1;i<num;i++){
            if(num % i == 0){
                sum = i+sum;
            }
        }

        if(sum == num){
            return true;
        }
        return false;
    }
}
