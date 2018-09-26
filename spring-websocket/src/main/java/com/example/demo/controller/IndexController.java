package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author alex_hu
 * @date 18-9-19 下午5:08
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

}
