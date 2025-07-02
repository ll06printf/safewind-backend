package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-07-02  17:04
 * @description: 查询角色用户参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleUserQueryDTO extends Page {
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 用户邮箱
     * */
    private String email;
}
