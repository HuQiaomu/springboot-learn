package com.alex.dubbo.controller;

import com.alex.dubbo.service.ProviderService;
import com.reger.dubbo.annotation.Inject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wh1507006
 * @date 2018/11/5 13:53
 */
@Component
@RestController
public class ConsumerController implements CommandLineRunner {

    // 使用兼容注入，可以使用dubbo原生注解@Reference注入
    @Inject
    public ProviderService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(service.sayHello("consumer alex"));
    }

    @GetMapping("/")
    public String hello(String name) {
        if (name == null) {
            name = "consumer alex";
        }

        return service.sayHello(name);
    }
}
