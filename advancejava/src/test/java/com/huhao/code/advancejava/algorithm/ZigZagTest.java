package com.huhao.code.advancejava.algorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by huhao on 2017/3/23.
 */
public class ZigZagTest {
    private ZigZag zigZag;

    @Before
    public void setUp(){
        zigZag = new ZigZag();
    }

    @Test
    public void tesZigZag(){
        String text = "Hello World.";
        String result = zigZag.convert(text,4);
        System.out.println(result);
    }

    @Test
    public void testZigZagWithClearcode(){
        String text = "Hello World.";
        String result = zigZag.convert2(text,4);
        System.out.println(result);

        String text2 = "A";
        String result1 = zigZag.convert2(text2,2);
        System.out.println(result1);

    }
}
