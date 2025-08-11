package com.safewind.application.controller.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-09  15:30
 * @description: 用户角色信息
 */
@Data
@Builder
public class UserRoleVO {
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;
}
