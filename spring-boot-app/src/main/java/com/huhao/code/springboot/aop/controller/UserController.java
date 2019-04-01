package com.huhao.code.springboot.aop.controller;

import com.huhao.code.springboot.aop.annotations.UserAccess;
import com.huhao.code.springboot.aop.utils.logger.InvokerLoggerBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Arthur Hu
 * @date: 2018/12/26 上午11:07
 * Description:
 */
@RestController
public class UserController {


    private static final Logger invokeLogger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/first")
    public Object first(){
        return "first controller";
    }


    @RequestMapping(value = "/doError")
    public Object error(){
        return 1 / 0;
    }

    @RequestMapping(value = "/second")
    @UserAccess(desc = "second")
    public Object second(HttpServletRequest request){
        invokeLogger.error("invoke in controller.");
        return "second";
    }
}
