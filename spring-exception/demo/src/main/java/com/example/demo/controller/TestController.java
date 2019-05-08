package com.example.demo.controller;

import com.example.demo.bean.ResponseBean;
import com.example.demo.contant.ErrorContant;
import com.example.demo.exception.BizException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wh1507006
 * @date 2019/4/22 13:35
 */
@RestController
public class TestController extends ExceptionController {

    @GetMapping("/test/{type}")
    public ResponseBean<String> test(@PathVariable("type") Integer type) {
        ResponseBean<String> responseBean = new ResponseBean<>();
        if (type == 1) {
            responseBean.setData("type equal 1");
        } else if (type == 2) {
            throw new BizException(ErrorContant.TEST_ERROR);
        } else {
            throw new BizException(ErrorContant.TEST_ERROR, "no message");
        }

        return responseBean;

    }

}
