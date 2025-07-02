package com.safewind.infra.basic.service;

import com.safewind.infra.basic.entity.RoleUser;
import com.safewind.infra.basic.entity.SysUserRole;

import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUserRole queryById(Long userId);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole insert(SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    SysUserRole update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

    /**
     * 批量插入数据
     *
     * @param sysUserRoleList 实例对象列表
     * @return 批量插入数据
     */
    long insertBatch(List<SysUserRole> sysUserRoleList);

    /**
     * 查询未分配用户
     *
     * @param roleUser 查询条件
     * @return 列表
     */
    List<RoleUser> queryUnDistributionRole(RoleUser roleUser);

    /**
     * 查询未分配用户
     *
     * @param sysUserRole@return 列表
     */
    long count(SysUserRole sysUserRole);

    /**
     * 查询未分配用户数量
     *
     * @param roleUser 查询条件
     * @return 数量
     */
    long queryUnDistributionRoleCount(RoleUser roleUser);
}
