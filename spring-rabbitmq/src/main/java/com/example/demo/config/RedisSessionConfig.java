package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author wh1507006
 * @date 2018/11/1 16:39
 */
//这个类用配置redis服务器的连接
//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 10)
public class RedisSessionConfig {

    //冒号后的值为没有配置文件时，制动装载的默认值
    @Value("${spring.redis.host:localhost}")
    String HostName;
    @Value("${spring.redis.port:6379}")
    int Port;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        connection.setPort(Port);
        connection.setHostName(HostName);
        return connection;
    }


}
