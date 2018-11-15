package com.example.demo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wh1507006
 * @date 2018/10/31 13:25
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "Hello, " +  i;
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
