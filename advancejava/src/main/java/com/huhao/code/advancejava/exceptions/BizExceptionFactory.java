package com.huhao.code.advancejava.exceptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 重复创建大量Excetion时用静态工厂，减少GC次数实验
 *
 *
 * Created by huhao on 2016/12/9.
 */
public class BizExceptionFactory {

    private static final String DEFAULT_BIZ_EXCEPTION = "DEFAULT LIMIT EXCEPTION";

    private static final Map<String, BizException> exceptionMap = new ConcurrentHashMap<String, BizException>();

    public static BizException newInstance(){
        return newInstance(DEFAULT_BIZ_EXCEPTION);
    }

    public static BizException newInstance(String message){
        BizException exception = exceptionMap.get(message);
        if(exception == null){
            exception = new BizException(message);
            exceptionMap.put(message,exception);
        }
        return exception;
    }

    // register Exception
    public static void registerDefaultException(BizException bizException){
        registerException(DEFAULT_BIZ_EXCEPTION,bizException);
    }

    public static void registerException(String message, BizException exception){
        exceptionMap.put(message,exception);
    }

}
