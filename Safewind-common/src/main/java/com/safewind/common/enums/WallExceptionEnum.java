package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:13
 * @description: 弹幕墙异常枚举
 */
@Getter
@AllArgsConstructor
public enum WallExceptionEnum implements BaseExceptionInterface {

    WALL_IS_NULL(4001, "弹幕数据不能为空"),
    WALL_ID_NOT_NULL(4002, "弹幕ID不能为空"),
    WALL_NOT_EXIST(4003, "弹幕不存在"),
    WALL_CONTENT_EMPTY(4004, "弹幕内容不能为空"),
    WALL_NAME_EMPTY(4005, "用户名字不能为空");

    private final Integer errorCode;
    private final String errorMessage;

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
