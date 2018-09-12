package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author alex_hu
 * @date 18-9-12 下午3:14
 */
@Slf4j
@Service
public class TestService {

    public String test(String str) {
        log.info("[service] - service 处理完成");
        return str;
    }

}
