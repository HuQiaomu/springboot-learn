package com.example.demo.error;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author wh1507006
 * @date 2018/11/1 14:49
 * 验证全局错误处理
 */
@ControllerAdvice  //RestControllerAdvice
@Component
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public String handler(HttpServletRequest request, ConstraintViolationException ex) {
        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            sb.append(violation.getMessage());
        }

        return sb.toString();
    }

}
