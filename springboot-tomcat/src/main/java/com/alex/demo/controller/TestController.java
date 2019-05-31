package com.alex.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wh1507006
 * @date 2019/5/31 9:49
 */
@RestController
public class TestController {

    @GetMapping("/")
    public String index() {
        return "springboot 使用外部 tomcat 部署实例。";
    }

}
