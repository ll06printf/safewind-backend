package com.safewind.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Darven
 * @CreateTime: 2025-03-26  19:09
 * @Description: 业务异常
 */
@Getter
@Setter
public class BizException  extends RuntimeException {

    // 异常码
    private Integer errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }

    public BizException(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
