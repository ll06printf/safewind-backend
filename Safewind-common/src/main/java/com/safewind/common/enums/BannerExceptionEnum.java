package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅异常枚举
 */
@AllArgsConstructor
@Getter
public enum BannerExceptionEnum implements BaseExceptionInterface {
    ID_NOT_NULL(5000, "横幅ID不能为空"),
    TITLE_NOT_NULL(5001, "横幅标题不能为空"),
    SUB_TITLE_NOT_NULL(5002, "横幅副标题不能为空"),
    BACKGROUND_PICTURE_NOT_NULL(5003, "背景图片不能为空"),
    ID_ERROR(5004, "横幅ID错误"),
    TITLE_ERROR(5005, "横幅标题错误"),
    SUB_TITLE_ERROR(5006, "横幅副标题错误"),
    BACKGROUND_PICTURE_ERROR(5007, "背景图片错误"),
    BANNER_IS_NULL(5008, "横幅不存在"),
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