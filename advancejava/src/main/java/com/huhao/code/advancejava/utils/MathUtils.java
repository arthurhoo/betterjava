/*-
 * #%L
 * guardian-core
 * %%
 * Copyright (C) 2016 - 2017 Ant Financial Services Group
 * %%
 * This software is developed by Ant Financial Services Group.This software and all the relevant information, 
 * including but not limited to any signs, images, photographs, animations, text, interface design, 
 * audios and videos, and printed materials, are protected by copyright laws and other intellectual property laws and treaties. 
 * The use of this software shall abide by the laws and regulations as well as Software Installation License Agreement/Software 
 * Use Agreement updated from time to time. Without authorization from Ant Financial Services Group , 
 * no one may conduct the following actions: 
 * 
 * 1) reproduce, spread, present, set up a mirror of, upload, download this software; 
 * 
 * 2) reverse engineer, decompile the source code of this software or try to find the source code in any other ways; 
 * 
 * 3) modify, translate and adapt this software, or develop derivative products, works, and services based on this software; 
 * 
 * 4) distribute, lease, rent, sub-license, demise or transfer any rights in relation to this software, 
 * or authorize the reproduction of this software on other computers.
 * #L%
 */

package com.huhao.code.advancejava.utils;

import java.math.RoundingMode;

import static java.lang.Math.abs;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

/**
 * 数学运算相关工具
 *
 * Created by huhao on 2017/1/16.
 */
public class MathUtils {
    public static long saturatedAdd(long a, long b) {
        long naiveSum = a + b;
        if ((a ^ b) < 0 | (a ^ naiveSum) >= 0) {
            // If a and b have different signs or a has the same sign as the result then there was no
            // overflow, return.
            return naiveSum;
        }
        // we did over/under flow, if the sign is negative we should return MAX otherwise MIN
        return Long.MAX_VALUE + ((naiveSum >>> (Long.SIZE - 1)) ^ 1);
    }

    /**
     * 除法
     * @param p
     * @param q
     * @param mode 四舍五入模式
     * @return
     */
    public static long divide(long p, long q, RoundingMode mode) {
        if (mode == null) {
            throw new NullPointerException();
        }
        long div = p / q; // throws if q == 0
        long rem = p - q * div; // equals p % q

        if (rem == 0) {
            return div;
        }

        /*
         * Normal Java division rounds towards 0, consistently with RoundingMode.DOWN. We just have to
         * deal with the cases where rounding towards 0 is wrong, which typically depends on the sign of
         * p / q.
         *
         * signum is 1 if p and q are both nonnegative or both negative, and -1 otherwise.
         */
        int signum = 1 | (int) ((p ^ q) >> (Long.SIZE - 1));
        boolean increment;
        switch (mode) {
            case UNNECESSARY:
                if (!(rem == 0)) {
                    throw new ArithmeticException(
                        "mode was UNNECESSARY, but rounding was necessary");
                }
                // fall through
            case DOWN:
                increment = false;
                break;
            case UP:
                increment = true;
                break;
            case CEILING:
                increment = signum > 0;
                break;
            case FLOOR:
                increment = signum < 0;
                break;
            case HALF_EVEN:
            case HALF_DOWN:
            case HALF_UP:
                long absRem = abs(rem);
                long cmpRemToHalfDivisor = absRem - (abs(q) - absRem);
                // subtracting two nonnegative longs can't overflow
                // cmpRemToHalfDivisor has the same sign as compare(abs(rem), abs(q) / 2).
                if (cmpRemToHalfDivisor == 0) { // exactly on the half mark
                    increment = (mode == HALF_UP | (mode == HALF_EVEN & (div & 1) != 0));
                } else {
                    increment = cmpRemToHalfDivisor > 0; // closer to the UP value
                }
                break;
            default:
                throw new AssertionError();
        }
        return increment ? div + signum : div;
    }
}
