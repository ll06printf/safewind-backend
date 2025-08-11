package com.safewind.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-11  13:28
 * @description: 角色菜单BO
 */
@Data
public class RoleMenuBO {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID列表
     */
    private List<Long> menuIds;
}
