package com.example.demo.controller;

import com.example.demo.entity.MyProperty1;
import com.example.demo.entity.MyProperty2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author alex_hu
 * @date 18-8-30 下午4:03
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private MyProperty1 myProperty1;

    @Autowired
    private MyProperty2 myProperty2;

    @Resource
    private HttpServletRequest httpServletRequest;

    @GetMapping("/1")
    public MyProperty1 myProperty1() {
        HttpSession session = httpServletRequest.getSession();
        log.info("session id: " + session.getId());
        log.info("==================================================");
        log.info(myProperty1.toString());
        log.info("==================================================");
        return myProperty1;
    }

    @GetMapping("/2")
    public MyProperty2 myProperty2() {
        log.info("==================================================");
        log.info(myProperty2.toString());
        log.info("==================================================");
        return myProperty2;
    }

}
