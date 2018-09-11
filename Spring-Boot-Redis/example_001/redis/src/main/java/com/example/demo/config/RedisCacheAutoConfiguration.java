package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

/**
 * @author alex_hu
 * @date 18-9-11 上午10:31
 */
/*
默认情况下的模板只能支持RedisTemplate<String, String>，也就是只能存入字符串，
这在开发中是不友好的，所以自定义模板是很有必要的，
当自定义了模板又想使用String存储这时候就可以使用StringRedisTemplate的方式，它们并不冲突…

//使用的　实体类需要序列化
 */

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

    private final static Logger log = LoggerFactory.getLogger(RedisCacheAutoConfiguration.class);

    /*
     cacheManager 配置信息，作用于使用缓存注解
     配置　key, value 的存储方式
     配置 key 的过期时间
      */
    @Bean
    public RedisCacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        //创建缓存注解配置类
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置缓存过期时间
                .entryTtl(Duration.ofSeconds(20))
                //　配置　key 的存储方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 配置　value 的存储方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();

        //　创建　redis 的缓存管理类
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .transactionAware()
                .build();

        log.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }


    /*
     redisCacheTemplate 配置信息作用与使用 redisCacheTemplate 直接保存数据到缓存中
     注意：
     @Autowired　// 根据 byValue 来注入实例的，所以名字要相同. @Bean(name = "redisTemplate") 可以直接指定名字
	 private RedisTemplate<String, Serializable> redisCacheTemplate; // 这里的名字要相同

	 该方法指定了使用该 template 时　key和value 的存储方式，并且设置　Lettuce　作为 redis 的连接池
      */
    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);

        log.debug("自定义RedisTemplate加载完成");
        return template;
    }


}
