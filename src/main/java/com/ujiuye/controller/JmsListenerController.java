package com.ujiuye.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JmsListenerController {

    @JmsListener(destination = "queue")            //没有RequestMapping也可以
    public void getJms(String msg){
        System.out.println("接收到内容： "+msg);
    }

}
