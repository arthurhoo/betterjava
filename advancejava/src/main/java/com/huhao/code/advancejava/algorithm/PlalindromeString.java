package com.huhao.code.advancejava.algorithm;

/**
 * Manacher Algorithm
 *
 * Created by huhao on 2017/3/23.
 */
public class PlalindromeString {

    private int[] p;
    private char[] t;

    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()){
            throw new IllegalArgumentException("parameter should not be null");
        }

        String result = null;
        preprocess(s);
        p = new int[t.length];

        int pos = 0,maxRight = 0;
        for(int i = 1;i<t.length-1;i++){
            int mirror = pos*2-i;

            if(maxRight > i){
                p[i] = Math.min(maxRight-i,p[mirror]); // 考虑mirror的回文的长度
            }

            while(t[i+p[i]] == t[i-p[i]]){
                p[i]++;
            }

            if(i+p[i] > maxRight){
                maxRight = i+p[i];
                pos = i;
            }
        }

        int length =0;
        int center = 0;
        for(int i=1;i<p.length-1;i++){
            if(p[i]>length){
                length = p[i];
                center = i;
            }
        }

        // 计算回文半径时中心节点计算在内，所以计算起始位置时需要减1
        result = s.substring((center-2-length)/2+1,(center-1+length)/2);

        return result;

    }

    public void preprocess(String s){
        t = new char[s.length()*2+3];
        t[0] = '$';
        t[s.length()*2+2] = '@';
        for(int i=0;i<s.length();i++){
            t[2*i+1] = '#';
            t[2*i+2] = s.charAt(i);
        }
        t[s.length()*2+1] = '#';
    }





}
