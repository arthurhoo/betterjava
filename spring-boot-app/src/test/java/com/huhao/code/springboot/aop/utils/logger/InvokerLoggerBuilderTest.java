package com.huhao.code.springboot.aop.utils.logger;

import org.junit.Test;

import ch.qos.logback.classic.Logger;

/**
 * @author: Arthur Hu
 * @date: 2018/12/26 下午6:07
 * Description:
 */
public class InvokerLoggerBuilderTest {

    @Test
    public void test_LoggerBuilder(){
        Logger invokeLogger = InvokerLoggerBuilder.getLogger(InvokerLoggerBuilderTest.class);

        invokeLogger.info("invoker logger info");
    }
}
