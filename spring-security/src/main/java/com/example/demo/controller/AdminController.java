package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex_hu
 * @date 18-9-18 下午4:50
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String getAdmin() {
        return "hello admin";
    }

}
