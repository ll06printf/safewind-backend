package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-15  01:24
 * @Description:  部门异常枚举
 *  * 定义部门相关的业务异常信息
 */
@Getter
@AllArgsConstructor
public enum DepartmentExceptionEnum implements BaseExceptionInterface {
    /**
     * 部门信息为空
     */
    DEPARTMENT_IS_NULL(4001, "部门信息不能为空"),

    /**
     * 部门ID为空
     */
    DEPARTMENT_ID_NOT_NULL(4002, "部门ID不能为空"),

    /**
     * 部门不存在
     */
    DEPARTMENT_NOT_EXIST(4003, "部门不存在"),

    /**
     * 部门名称已存在
     */
    DEPARTMENT_NAME_EXIST(4004, "部门名称已存在");

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
