package com.alex.shiro.error;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wh1507006
 * @date 2018/11/7 17:08
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public String noPermission(HttpServletResponse response) {
        response.setStatus(401);
        return "no permission";
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(Exception ex) {
        return ex.getMessage();
    }

}
