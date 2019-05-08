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
public class Consumer1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer1.class);

    @JmsListener(destination = "sample.queue")
    public void receiveMsg(String msg) {
        LOGGER.info("consumer1 - {}", msg);
    }

}
