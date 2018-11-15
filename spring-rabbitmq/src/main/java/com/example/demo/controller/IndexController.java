package com.example.demo.controller;

import com.example.demo.entity.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author wh1507006
 * @date 2018/11/1 14:24
 */
@Controller
@RequestMapping("/test")
@Validated
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
        System.out.println(loginForm);
        return "success";
    }

    @ResponseBody
    @GetMapping("/detail")
    public String detail(@NotNull(message = "not empty") Long id) {
        return "success";
    }

}
