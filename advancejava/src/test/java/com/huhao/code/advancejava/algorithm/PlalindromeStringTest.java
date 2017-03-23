package com.huhao.code.advancejava.algorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by huhao on 2017/3/23.
 */
public class PlalindromeStringTest {

    PlalindromeString plalindromeString;

    @Before
    public void setUp(){
        plalindromeString = new PlalindromeString();
    }

    @Test
    public void testPlalindromeString(){
        String a = "aabbaacdj";
        String result = plalindromeString.longestPalindrome(a);
        System.out.println(result);
        String b = "acddbdkkkdjke";
        System.out.println(plalindromeString.longestPalindrome(b));
    }

}
