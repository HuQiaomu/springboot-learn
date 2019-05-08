package com.example.demo.controller;

import com.example.demo.bean.ResponseBean;
import com.example.demo.exception.BizException;
import com.example.demo.util.MessageUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wh1507006
 * @date 2019/4/22 13:09
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BizException.class)
    public ResponseBean<String> bizException(BizException bizException) {
        return handleError(bizException);
    }

    private ResponseBean<String> handleError(Exception exception) {
        ResponseBean<String> responseBean = new ResponseBean<>();
        responseBean.setSuccess(false);

        if (exception instanceof BizException) {
            BizException bizException = (BizException) exception;
            responseBean.setCode(bizException.getCode());
            if (StringUtils.isEmpty(bizException.getMessage())) {
                responseBean.setMessage(MessageUtil.getMessage(bizException.getCode()));
            } else {
                responseBean.setMessage(bizException.getMessage());
            }
        }
        return responseBean;
    }


}
