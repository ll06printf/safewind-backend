package com.safewind.application.controller.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-07-02  12:04
 * @description: 角色用户关联接收实体
 */
@Data
public class RoleUserDTO {
    /**
     * 角色 id
     * */
    private Long roleId;
    /**
     * 用户id列表
     * */
    private List<Long> userIds;
}
