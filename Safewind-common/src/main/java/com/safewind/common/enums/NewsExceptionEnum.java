package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-08-12  00:55
 * @description: 新闻异常枚举
 */
@AllArgsConstructor
@Getter
public enum NewsExceptionEnum implements BaseExceptionInterface {
    ID_NOT_NULL(4000, "新闻ID不能为空"),
    TITLE_NOT_NULL(4001, "新闻标题不能为空"),
    CONTENT_NOT_NULL(4002, "新闻内容不能为空"),
    STATUS_NOT_NULL(4003, "新闻状态不能为空"),
    ID_ERROR(4004, "新闻ID错误"),
    TITLE_ERROR(4005, "新闻标题错误"),
    CONTENT_ERROR(4006, "新闻内容错误"),
    STATUS_ERROR(4007, "新闻状态错误"),
    NEWS_IS_NULL(4008, "新闻不存在"),
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
