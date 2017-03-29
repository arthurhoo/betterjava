package com.huhao.code.advancejava.algorithm;

/**
 * Created by huhao on 2017/3/24.
 */

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 */
public class IntegerReverse {

    public int reverse(int x){
        if(x < Integer.MIN_VALUE || x > Integer.MAX_VALUE){
            throw new IllegalArgumentException("parameter is illegal.");
        }
        int reult = 0;

        if(x == 0){
            return reult;
        }

        long tempResult = 0;

        Queue<Integer> stack = new LinkedList<Integer>();

        int quotient = (x>>31)==0? x:(~x+1);
        int remainder = 0;
        int isPositive = 1;
        if(x < 0){
            isPositive = -1;
        }


        while(quotient > 0){
            remainder = quotient%10;
            stack.offer(remainder);
            quotient = quotient/10;
        }
        int firstNoZero = 0;
        while(!stack.isEmpty()){
            int top = stack.poll();
            if(firstNoZero == 0 && top != 0){
                firstNoZero = 1;
            }
            if(firstNoZero == 0){
                continue;
            }

            tempResult = tempResult*10+top;
        }
        if(tempResult < Integer.MIN_VALUE || tempResult > Integer.MAX_VALUE){
            return 0;
        }
        reult = (int)tempResult * isPositive;

        return reult;

    }

    /**
     * better method
     * If overflow exists, the new result will not equal previous one.
     */
    public int reverse2(int x)
    {
        int result = 0;

        while (x != 0)
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x / 10;
        }

        return result;
    }
}
