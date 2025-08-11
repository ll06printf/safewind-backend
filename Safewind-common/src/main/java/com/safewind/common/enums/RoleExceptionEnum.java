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
    ID_NOT_NULL(4008,"角色ID不能为空/ID错误"),
    ROLE_IS_NULL(4009,"角色不存在"),
    ROLE_EXIST(4010,"角色已存在"),
    ROLE_ADD_ERROR(4011,"角色添加失败"),
    ROLE_UPDATE_ERROR(4012,"角色更新失败"),
    ROLE_DELETE_ERROR(4013,"角色删除失败"),
    ROLE_DISTRIBUTION_ERROR(4014,"角色分配失败"),
    ROLE_USER_ADD_ERROR(4015,"用户分配添加失败"),
    MENU_ID_LIST_NULL(4016,"menuIds不存在"),
    USER_ID_LIST_ERROR(4017, "参数错误"),
    USER_ROLE_EXIST(4018, "用户已经分配"),
    ROLE_HAS_MENU(4019, "角色已经绑定菜单"),
    ROLE_HAS_USER(4020, "角色已经绑定用户"),
    MENU_ID_LIST_ERROR(4021, "菜单不存在，请确保菜单ID存在");
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
