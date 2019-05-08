package com.example.demo.bean;

/**
 * @author wh1507006
 * @date 2019/4/22 13:28
 */
public class ResponseBean<T> {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 成功状态
     */
    private Boolean success;

    /**
     * 数据
     */
    private T data;

    public ResponseBean() {
        this.success = true;
        this.code = "000000";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
