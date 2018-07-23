package com.lovelee.springbootmybatis.utils;
/*
 * 公共api返回模板
 *
 * @author Lee
 * @date 2018/7/13
 * @param
 * @return
 */
public class ApiReponse <T> {
    private static final String CODE_SUCCESS = "0";
    private static final String CODE_FAIL = "-1";

    private String code;
    private T data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ApiReponse() {
    }

    public ApiReponse(String code) {
        this.code = code;
    }

    public ApiReponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public ApiReponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiReponse success(Object obj) {
        return new ApiReponse(CODE_SUCCESS, obj);
    }

    public static ApiReponse fail(String msg) {
        return new ApiReponse(CODE_FAIL, msg);
    }

    public static ApiReponse writeCode(String errorCode) {
        return new ApiReponse(errorCode);
    }
}
