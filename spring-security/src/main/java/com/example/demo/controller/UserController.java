package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex_hu
 * @date 18-9-14 下午2:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "hello user";
    }

}
