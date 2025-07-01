package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-07-01  13:49
 * @description: 角色查询参数
 */
@Data
public class RoleQueryDTO extends Page {
    /**
     * 角色名称 例如：管理员
     * */
    private String roleName;
    /**
     * 角色key 例如：admin
     * */
    private String roleKey;
    /**
     * 状态 0正常 1禁用
     * */
    private String status;
}
