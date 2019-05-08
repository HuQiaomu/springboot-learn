package com.example.demo.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * @author wh1507006
 * @date 2019/4/23 10:00
 *
 * activemq 两种消息模型
 *
 * point-to-point
 * publish/subscribe , 需配置 spring.jms.pub-sub-domain = true
 *
 */
@Configuration
public class ActiveMQConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sample.topic");
    }

}
