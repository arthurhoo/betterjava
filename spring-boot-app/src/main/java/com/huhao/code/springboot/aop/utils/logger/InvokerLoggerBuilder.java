package com.huhao.code.springboot.aop.utils.logger;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.util.OptionHelper;

/**
 * @author: Arthur Hu
 * @date: 2018/12/26 下午4:26
 * Description:
 */
public class InvokerLoggerBuilder {

    private static final ConcurrentHashMap<String,Logger> container = new ConcurrentHashMap<>();

    public static Logger getLogger(Class<?> clazz){
        String key = clazz.getCanonicalName();
        Logger logger = container.get(key);
        if(null != logger){
            return logger;
        }

        logger = build(InvokerLoggerBuilder.class);

        container.put(key, logger);

        return logger;
    }


    /**
     * build logger
     * @param name
     * @return
     */
    private synchronized static Logger build(Class<?> clazz){
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();



        Logger logger = context.getLogger(clazz);
        logger.setAdditive(false);
        logger.setLevel(Level.INFO);
        RollingFileAppender appender = new RollingFileAppender();
        appender.setContext(context);
        appender.setName("invokeLogAppender");

        String filePathStr = "/Users/huhao/mycode/betterjava/spring-boot-app/invoke/";


        appender.setFile(OptionHelper.substVars(filePathStr+"invokeLog" + ".log",context));
        appender.setAppend(true);
        appender.setPrudent(false);
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        String fp = OptionHelper.substVars(filePathStr+"invokeLog"+ ".log.%d{yyyy-MM-dd}.%i",context);

        policy.setFileNamePattern(fp);
        policy.setMaxHistory(15);
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd/HH:mm:ss.SSS}|%X{localIp}|[%t] %-5level %logger{50} %line - %m%n");
        encoder.start();

        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();
        logger.addAppender(appender);
        return logger;
    }

}
