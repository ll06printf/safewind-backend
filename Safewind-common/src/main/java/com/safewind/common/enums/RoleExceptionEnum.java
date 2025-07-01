package com.safewind.common.enums;

import com.safewind.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Darven
 * @createTime: 2025-07-01  13:39
 * @description: TODO
 */
@AllArgsConstructor
@Getter
public enum RoleExceptionEnum implements BaseExceptionInterface {
    NAME_NOT_NULL(4000,"角色名称不能为空"),
    KEY_NOT_NULL(4001,"角色标识不能为空"),
    PERM_NOT_NULL(4002,"权限标识不能为空"),
    STATUS_NOT_NULL(4003,"角色状态不能为空"),
    DEL_NOT_NULL(4004,"角色删除状态不能为空"),
    CREATE_NOT_NULL(4005,"角色创建者不能为空"),
    UPDATE_NOT_NULL(4006,"角色更新者不能为空"),
    REMARK_NOT_NULL(4007,"角色备注不能为空"),
    ID_NOT_NULL(4008,"角色ID不能为空"),
    ROLE_IS_NULL(4009,"角色不存在"),
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
