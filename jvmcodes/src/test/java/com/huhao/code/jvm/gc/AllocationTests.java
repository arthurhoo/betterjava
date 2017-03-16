package com.huhao.code.jvm.gc;

import org.junit.Test;

/**
 * 测试jvm内存分配机制 Created by huhao on 2017/2/26.
 */
public class AllocationTests {

    private static final int _1MB = 1024 * 1024;

    /**
     * jvm设定参数：-Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     */
    @Test
    public void testAllocation() {
        byte[] allo1, allo2, allo3, allo4;
        allo1 = new byte[2 * _1MB];
        allo2 = new byte[2 * _1MB];
        allo3 = new byte[2 * _1MB];
        allo4 = new byte[4 * _1MB];
    }

    public static void main(String[] args){
        AllocationTests allocationTests = new AllocationTests();
        allocationTests.testAllocation();
    }

}
