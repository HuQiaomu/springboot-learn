package com.example.demo.controller;

import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex_hu
 * @date 18-9-12 下午2:41
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String index() {
        log.info("[controller] - 处理完成");
        return "spring boot 拦截器，过滤器，监听器，AOP　测试示例";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/aop")
    public String aopTest() {
        String result = testService.test("AOP 测试");
        log.info("[AOP] - controller 处理完成");
        return result;
    }

}
