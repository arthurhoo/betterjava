package com.huhao.code.advancejava.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by huhao on 2017/3/24.
 */
public class IntegerReverseTest {

    IntegerReverse integerReverse;

    @Before
    public void setUp(){
        integerReverse = new IntegerReverse();
    }

    @Test
    public void  testReverseInteger(){
        int a = 2345;
        int result = integerReverse.reverse(a);
        System.out.println(result);
        Assert.assertEquals(5432,result);

        int b = -1234;
        int result1 = integerReverse.reverse(b);
        System.out.println(result1);
        Assert.assertEquals(-4321,result1);

        int c = 1534236469;

        int result2 = integerReverse.reverse(c);
        System.out.println(result2);
        Assert.assertEquals(0,result2);

        int d = 901000;
        int result3 = integerReverse.reverse(d);
        System.out.println(result3);
        Assert.assertEquals(109,result3);



    }
}
