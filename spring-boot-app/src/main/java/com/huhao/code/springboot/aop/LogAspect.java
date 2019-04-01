package com.huhao.code.springboot.aop;

import com.huhao.code.springboot.aop.annotations.UserAccess;
import com.huhao.code.springboot.aop.utils.logger.InvokerLoggerBuilder;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.classic.Logger;

/**
 * @author: Arthur Hu
 * @date: 2018/12/26 上午11:04
 * Description:
 */

@Aspect
@Component
public class LogAspect {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogAspect.class);


//    @Pointcut("execution(public * com.huhao.code.springboot.aop.controller.*.*(..))")
//    public void webLog(){
//        System.out.println("web Log itself");
//    }
//
//
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint){
//
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        System.out.println("URL : " + request.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + request.getMethod());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturn(Object ret){
//        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret);
//    }
//
//    @AfterThrowing("webLog()")
//    public void throwException(JoinPoint joinPoint){
//        System.out.println("方法异常时执行.....");
//    }
//
//    @After("webLog()")
//    public void after(JoinPoint joinPoint){
//        System.out.println("方法最后执行.....");
//    }
//
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint proceedingJoinPoint){
//        System.out.println("方法环绕start.....");
//        try {
//            Object o =  proceedingJoinPoint.proceed();
//            System.out.println("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    @Pointcut(value = "@annotation(com.huhao.code.springboot.aop.annotations.UserAccess)")
    public void access(){}

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{
        System.out.println("joinpoint = "+joinPoint.toString());
        System.out.println("second before");
    }

    @Around("@annotation(userAccess)")
    public Object invokeAround(ProceedingJoinPoint proceedingJoinPoint, UserAccess userAccess){


        logger.info("invoke Arround");

        //获取注解里的值
        System.out.println("second around:" + userAccess.desc());
        try {
            System.out.println("around proceedingJpinPoint = "+proceedingJoinPoint.toString());


            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
