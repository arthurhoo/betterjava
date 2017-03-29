package com.huhao.code.advancejava;

import com.huhao.code.advancejava.algorithm.BitAlgorithm;
import com.huhao.code.advancejava.utils.BitUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by huhao on 2017/3/23.
 */
public class BitUtilsTest {

    @Test
    public void isEvenTest(){
        int a = 31;
        Assert.assertFalse(BitUtils.isEven(a));
        int b = 32;
        Assert.assertTrue(BitUtils.isEven(b));
        int c = -32;
        Assert.assertTrue(BitUtils.isEven(c));
        int d = -35;
        Assert.assertFalse(BitUtils.isEven(d));
        int e = 0;
        Assert.assertTrue(BitUtils.isEven(e));

    }

    @Test
    public void testIntReverse(){
        int a = 235;
        int b = Integer.reverse(a);
        System.out.println(b);
    }

    @Test
    public void testOppositeNumber(){
        int a = -234;
        Assert.assertEquals(234,BitUtils.oppositeNumber(a));
        int b = 546;
        Assert.assertEquals(-546,BitUtils.oppositeNumber(b));
        int c = 0;
        Assert.assertEquals(0,BitUtils.oppositeNumber(c));


    }

    @Test
    public void testAbs(){
        int a = -234;
        Assert.assertEquals(234,BitUtils.abs(a));
        int b = 546;
        Assert.assertEquals(546,BitUtils.abs(b));
        int c = 0;
        Assert.assertEquals(0,BitUtils.abs(c));
    }

    @Test
    public void testAbs2(){
        int a = -234;
        Assert.assertEquals(234,BitUtils.abs2(a));
        int b = 546;
        Assert.assertEquals(546,BitUtils.abs2(b));
        int c = 0;
        Assert.assertEquals(0,BitUtils.abs2(c));
    }

    @Test
    public void testFindLostNum(){
        BitAlgorithm bitAlgorithm = new BitAlgorithm();
        int[] nums = {1,2,1,3,3,5,2,587,434,678,587,434,678,9,5};
        int result = bitAlgorithm.findLostNumber(nums);
        Assert.assertEquals(9,result);
    }
}
