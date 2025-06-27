package com.safewind.common.utils;

import com.safewind.common.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Darven
 * @CreateTime: 2025-03-26  19:10
 * @Description: 返回类
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    protected T data;
    private Boolean success;

    public Result() {
    }

    public Result(Integer code, String message, T data, Boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    private static <T> Result<T> build(Integer code, String message, T data, Boolean success) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(success);
        return result;
    }

    //==============================成功=========================

    public static <T> Result<T> success(T data) {
        return build(200, "success", data, true);
    }

    public static <T> Result<T> success(Integer code,T data) {
        return build(code, "success", data, true);
    }

    public static <T> Result<T> success(Integer code,String message,T data) {
        return build(code, message, data, true);
    }


    public static <T> Result<T> success(Integer code,String message) {
        return build(code, message, null, true);
    }

    public static <T> Result<T> success(T data,String message) {
        return build(ResultCodeEnum.SUCCESS.getCode(), message, data, true);
    }

    public static <T> Result<T> success(Integer code) {
        return build(code, "success", null, true);
    }

    public static <T> Result<T> success() {
        return build(ResultCodeEnum.SUCCESS.getCode(), "success", null, true);
    }


    public static <T> Result<T> success(String message,Integer code) {
        return build(code, message, null, true);
    }

    public static <T> Result<T> success(String message,Integer code,T data) {
        return build(code, message, data, true);
    }

    public static <T> Result<T> success(String message){
        return build(ResultCodeEnum.SUCCESS.getCode(),message,null,true);
    }
    //==============================失败=========================

    public static <T> Result<T> fail(T data) {
        return build(ResultCodeEnum.FAIL.getCode(), "fail", data, false);
    }
    public static <T> Result<T> fail(String message) {
        return build(ResultCodeEnum.FAIL.getCode(), message, null, false);
    }
    public static <T> Result<T> fail() {
        return build(ResultCodeEnum.FAIL.getCode(), "fail", null, false);
    }
    public static <T> Result<T> fail(Integer code,String message) {
        return build(code, message, null, false);
    }
    public static <T> Result<T> fail(Integer code,String message,T data) {
        return build(code, message, data, false);
    }
    public static <T> Result<T> fail(Integer code) {
        return build(code, "fail", null, false);
    }
    public static <T> Result<T> fail(String message,T data) {
        return build(ResultCodeEnum.FAIL.getCode(), message, data, false);
    }
    public static <T> Result<T> fail(String message,Integer code) {
        return build(code, message, null, false);
    }
    public static <T> Result<T> fail(String message,Integer code,T data) {
        return build(code, message, data, false);
    }
    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum) {
        return build(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null, false);
    }
    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum,T data) {
        return build(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data, false);
    }
    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum,String message) {
        return build(resultCodeEnum.getCode(), message, null, false);
    }


}
