package com.huhao.code.advancejava.scheduler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by huhao on 2017/1/30.
 */
public class MockTaskTest {

    @Test
    public void testClone(){
        MockTask mockTask = new MockTask(1L,200L);
        MockTask mockTask2 = (MockTask) mockTask.clone();
        mockTask2.setOrder(2L);
        Assert.assertTrue(mockTask.getOrder().equals(1L));
        Assert.assertEquals(2L,mockTask2.getOrder().longValue());
    }
}
