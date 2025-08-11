package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.*;

import java.util.List;

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
    // 分配用户
    boolean distributionRole(RoleUserBO roleUserBO);
    // 未分配的角色列表
    PageResult<RoleUserListBO> queryUnDistributionRole(RoleUserQueryBO roleUserQueryBO);
    // 已分配的角色列表
    PageResult<RoleUserListBO> queryDistributionRole(RoleUserQueryBO roleUserQueryBO);
    // 批量取消角色的用户
    boolean batchCancelAuthorizeUser(RoleUserBO roleUserBO);
    // 分配单个用户
    boolean distributionSingleRole(RoleUserBO roleUserBO);

    /**
     * 查询角色已分配的菜单权限
     */
    List<RoleMenuListBO> queryRoleMenus(Long roleId);

    /**
     * 分配菜单权限给角色
     */
    boolean assignMenusToRole(RoleMenuBO roleMenuBO);

    /**
     * 查询所有菜单（包含是否已分配给角色的标识）
     */
    List<RoleMenuListBO> queryAllMenusWithRoleStatus(Long roleId);
}
