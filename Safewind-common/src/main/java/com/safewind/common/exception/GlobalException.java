package com.safewind.common.exception;

import com.safewind.common.enums.ResultCodeEnum;
import com.safewind.common.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-21  15:19
 * @Description: 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalException {
    /**
     * 捕获自定义业务异常
     *
     * @return
     */
    @ExceptionHandler({BizException.class})
    @ResponseBody
    public Result<Object> handleBizException(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Result.fail(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 捕获参数校验异常
     *
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        // 参数错误异常码
        Integer errorCode = ResultCodeEnum.VALIDATION_FAILED.getCode();

        // 获取 BindingResult
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();

        // 获取校验不通过的字段，并组合错误信息，格式为： email 邮箱格式不正确, 当前值: '123124qq.com';
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error ->
                    sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(", 当前值: '")
                            .append(error.getRejectedValue())
                            .append("'; ")

            );
        });

        // 错误信息
        String errorMessage = sb.toString();

        log.warn("{} request error, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);

        return Result.fail(errorMessage, errorCode);
    }

    /**
     * 其他类型异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result<Object> handleOtherException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return Result.fail(ResultCodeEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数检验
     *
     * @param request,e 请求体和错误
     * @return 返回错误信息和打印日志
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public Result<Object> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        log.warn("{} param validated ,", request.getRequestURI(), e);
        return Result.fail(ResultCodeEnum.VALIDATION_FAILED, e.getMessage());
    }

    /**
     * @param: request 请求
     * @param: e 参数
     * @return Result<Object>
     * @author Darven
     * @date 2025/6/27 12:12
     * @description:
     */
    @ExceptionHandler({NullPointerException.class})
    public Result<Object> handleNullPointerException(HttpServletRequest request, NullPointerException e) {
        log.error("{},param is null",request.getRequestURI(),e);
        return Result.fail(ResultCodeEnum.VALIDATION_FAILED,e.getMessage());
    }
}
