package com.alex.springboottomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wh1507006
 * @date 2018/11/5 16:43
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "hello, springboot tomcat";
    }

}
