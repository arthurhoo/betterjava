package com.huhao.code.advancejava.utils;

/**
 * 基本的位操作应用工具
 *
 * Created by huhao on 2017/3/23.
 */
public class BitUtils {

    /**
     * 只要根据最未位是0还是1来决定，为0就是偶数，为1就是奇数。
     * 因此可以用if ((a & 1) == 0)代替if (a % 2 == 0)来判断a是不是偶数。
     * @param a
     * @return
     */
    public static boolean isEven(int a){
        return (a & 1)==0;
    }

    public static int oppositeNumber(int a){
        return ~a +1;
    }

    public static int abs(int a){
        int i = a >> 31;
        return i == 0?a:(~a+1);
    }

    /**
     * 对于任何数，与0异或都会保持不变
     * 与-1即0xFFFFFFFF异或就相当于取反
     */
    public static int abs2(int a){
        int i = a>>31;
        return ((a^i)-i);
    }

}
