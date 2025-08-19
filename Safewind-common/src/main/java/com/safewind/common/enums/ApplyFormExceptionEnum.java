package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-15  01:26
 * @Description: 申请表异常枚举
 *  * 定义申请表相关的业务异常信息
 */
@Getter
@AllArgsConstructor
public enum ApplyFormExceptionEnum implements BaseExceptionInterface {
    /**
     * 申请信息为空
     */
    APPLY_FORM_IS_NULL(4001, "申请信息不能为空"),

    /**
     * 申请ID为空
     */
    APPLY_FORM_ID_NOT_NULL(4002, "申请ID不能为空"),

    /**
     * 申请记录不存在
     */
    APPLY_FORM_NOT_EXIST(4003, "申请记录不存在"),

    /**
     * 学号已存在申请记录
     */
    STUDENT_ID_EXIST(4004, "该学号已提交申请"),

    /**
     * 审核状态为空
     */
    REVIEW_STATUS_NOT_NULL(4005, "审核状态不能为空"),

    /**
     * 审核记录不存在
     */
    REVIEW_NOT_EXIST( 4006, "审核记录不存在");

    /**
     * 错误代码
     */
    private final Integer errorCode;

    /**
     * 错误信息
     */
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
