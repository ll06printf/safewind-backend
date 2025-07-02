package com.safewind.domain.bo;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-07-02  17:36
 * @description: 角色用户查询参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleUserQueryBO extends Page {
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 用户邮箱
     * */
    private String email;
}
