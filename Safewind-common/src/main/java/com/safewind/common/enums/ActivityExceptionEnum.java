package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:17
 * @description: TODO
 */
@AllArgsConstructor
@Getter
public enum ActivityExceptionEnum implements BaseExceptionInterface{
    ACTIVITY_IS_NULL(4001, "活动参数为空"),
    ACTIVITY_ID_NOT_NULL(4002, "活动ID不能为空"),
    ACTIVITY_NOT_EXIST(4003, "活动不存在"),
    ;

    private final Integer code;
    private final String message;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
