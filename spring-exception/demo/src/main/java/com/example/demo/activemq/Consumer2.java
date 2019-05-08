package com.example.demo.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author wh1507006
 * @date 2019/4/23 10:08
 */
@Component
public class Consumer2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer2.class);

    @JmsListener(destination = "sample.topic")
    public void receiveMsg(String msg) {
        LOGGER.info("consumer2 - {}", msg);
    }

}
