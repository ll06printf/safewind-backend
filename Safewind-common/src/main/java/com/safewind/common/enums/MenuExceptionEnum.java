package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-27  12:22
 * @Description: 菜单异常处理枚举类
 */
@AllArgsConstructor
@Getter
public enum MenuExceptionEnum implements BaseExceptionInterface {
    NAME_NOT_NULL(3000,"菜单名称不能为空"),
    TYPE_NOT_NULL(3001,"菜单类型不能为空"),
    COMPONENT_NOT_NULL(3002,"组件路径不能为空"),
    PERM_NOT_NULL(3003,"权限标识符号不能为空")
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
