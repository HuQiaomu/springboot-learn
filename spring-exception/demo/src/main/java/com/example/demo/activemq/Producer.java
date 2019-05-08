package com.example.demo.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author wh1507006
 * @date 2019/4/23 10:03
 */
@Component
@EnableScheduling
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000) //每 3s 执行 1次
    public void sendMsg() {
        //send to queue
        this.jmsMessagingTemplate.convertAndSend(this.queue, "hi, activemq(queue)");
        //send to topic
        this.jmsMessagingTemplate.convertAndSend(this.topic, "hi, activemq(topic)");
    }

}
