package com.example.demo.exception;

/**
 * @author wh1507006
 * @date 2019/4/22 13:15
 */
public class BizException extends RuntimeException {

    private String code;

    public BizException(String code) {
        this.code = code;
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
