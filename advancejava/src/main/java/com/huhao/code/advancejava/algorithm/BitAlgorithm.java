package com.huhao.code.advancejava.algorithm;

/**
 * Created by huhao on 2017/3/24.
 */
public class BitAlgorithm {

    /**
     * 很多成对出现数字保存在磁盘文件中，注意成对的数字不一定是相邻的，
     * 如2, 3, 4, 3, 4, 2……，由于意外有一个数字消失了，如何尽快的找到是哪个数字消失了？
     */
    public int findLostNumber(int[] nums){
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("parameter should not be null");
        }
        int lostNum = 0;
        for(int i=0;i<nums.length;i++){
            lostNum ^= nums[i];
        }

        return lostNum;
    }
}
