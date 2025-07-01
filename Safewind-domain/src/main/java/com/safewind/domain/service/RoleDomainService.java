package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.RoleBO;
import com.safewind.domain.bo.RoleListBO;

/**
 * @author: Darven
 * @createTime: 2025-07-01  10:51
 * @description: 角色领域服务
 */
public interface RoleDomainService {
    // 查询角色列表
    PageResult<RoleListBO> queryRole(RoleBO roleBO);
    // 添加角色
    Boolean addRole(RoleBO roleBO);
    // 修改角色
    Boolean updateRole(RoleBO roleBO);
    // 删除角色
    boolean deleteRole(Long roleId);
}
