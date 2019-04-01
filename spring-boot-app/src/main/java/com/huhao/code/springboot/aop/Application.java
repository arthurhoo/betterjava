package com.huhao.code.springboot.aop;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Arthur Hu
 * @date: 2018/12/26 上午11:18
 * Description:
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.run(args);
    }

}
