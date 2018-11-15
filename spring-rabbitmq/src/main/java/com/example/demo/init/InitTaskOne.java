package com.example.demo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wh1507006
 * @date 2018/11/1 13:28
 */
@Component
@Order(1)
public class InitTaskOne implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("init task one .....");
    }
}
