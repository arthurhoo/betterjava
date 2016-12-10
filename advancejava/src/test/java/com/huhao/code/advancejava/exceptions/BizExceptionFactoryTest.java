package com.huhao.code.advancejava.exceptions;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 测试静态工厂方法创建Exception
 *
 * Created by huhao on 2016/12/9.
 */
public class BizExceptionFactoryTest {

    @Test
    public void test_createException(){
        BizException bizException = BizExceptionFactory.newInstance();
        Assert.assertNotNull(bizException);
        Assert.assertEquals("message is same.","DEFAULT LIMIT EXCEPTION",bizException.getMessage());

        BizException bizException1 = BizExceptionFactory.newInstance("customer biz exception");
        Assert.assertNotNull(bizException1);
        Assert.assertEquals("message is same","customer biz exception",bizException1.getMessage());
    }

    @Test
    public void test_singleton(){
        BizException bizException1 = BizExceptionFactory.newInstance("single");

        BizException bizException2 = BizExceptionFactory.newInstance("single");

        Assert.assertNotNull(bizException1);

        Assert.assertNotNull(bizException2);

        Assert.assertTrue(bizException1==bizException2);
    }


}
