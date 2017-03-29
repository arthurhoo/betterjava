package com.huhao.code.advancejava.algorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by huhao on 2017/3/26.
 */
public class NumberAlgorithmTest {
    private NumberAlgorithm numberAlgorithm;
    @Before
    public void setUp(){
        numberAlgorithm = new NumberAlgorithm();
    }

    @Test
    public void testNumAlgorithm(){
        int a = 99999991;
        boolean result = numberAlgorithm.checkPerfectNumber(a);
        System.out.println(result);
    }
}
