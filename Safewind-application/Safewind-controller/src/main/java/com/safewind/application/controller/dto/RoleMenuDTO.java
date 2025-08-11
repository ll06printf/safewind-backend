package com.safewind.application.controller.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-11  13:27
 * @description: 角色菜单分配DTO
 */
@Data
public class RoleMenuDTO {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID列表
     */
    private List<Long> menuIds;
}
