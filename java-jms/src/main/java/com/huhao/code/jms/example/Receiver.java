package com.huhao.code.jms.example;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author: Arthur Hu
 * @date: 2019/4/1 下午6:20
 * Description:
 */

@Component
public class Receiver {


    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email){
        System.out.println("Received <" + email + ">");
    }
}
