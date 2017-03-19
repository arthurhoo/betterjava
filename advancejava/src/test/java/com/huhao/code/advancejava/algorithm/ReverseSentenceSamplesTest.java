package com.huhao.code.advancejava.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by huhao on 2017/3/19.
 */
public class ReverseSentenceSamplesTest {

    private ReverseSentenceSamples reverseSentenceSamples;

    @Before
    public void setUp(){
        reverseSentenceSamples = new ReverseSentenceSamples();
    }

    @Test
    public void testReverseSentence1(){
        String sentence = "You are a good boy";
        System.out.println(sentence);
        String reverse1 = reverseSentenceSamples.reverseSentence1(sentence);
        System.out.println(reverse1);
        Assert.assertEquals("boy good a are You",reverse1);
        String sentence2 = "You are a good boy ";
        System.out.println(sentence2);
        String reverse2 = reverseSentenceSamples.reverseSentence1(sentence2);
        System.out.println(reverse2);
        Assert.assertEquals("boy good a are You",reverse2);

    }
}
