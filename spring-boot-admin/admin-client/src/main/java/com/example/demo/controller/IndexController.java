package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex_hu
 * @date 18-9-20 下午2:48
 */

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "spring boot admin client";
    }

}
