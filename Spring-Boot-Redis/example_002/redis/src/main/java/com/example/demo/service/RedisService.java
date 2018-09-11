package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author alex_hu
 * @date 18-8-27 下午1:14
 */


@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    public void setEx(String key, Object value, long expireTime) {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS); //设置该 key 的过期时间
    }

    public Object get(String key) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

}
