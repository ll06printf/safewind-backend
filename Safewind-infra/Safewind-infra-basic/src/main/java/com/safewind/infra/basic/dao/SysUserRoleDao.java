package com.safewind.infra.basic.dao;

import com.safewind.infra.basic.entity.RoleUser;
import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysUserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUserRole queryById(Long userId);


    /**
     * 统计总行数
     *
     * @param sysUserRole 查询条件
     * @return 总行数
     */
    long count(SysUserRole sysUserRole);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserRole> entities);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int update(SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Long roleId);

    /**
     * 查询未分配角色的用户
     *
     * @param roleUser 实体对象
     * @return  列表
     */
    List<RoleUser> queryUnDistributionRole(@Param("roleUser") RoleUser roleUser);

    /**
     * 查询未分配角色的用户数量
     *
     * @param roleUser 角色用户
     * @return 数量
     */
    long queryUnDistributionRoleCount(@Param("roleUser")RoleUser roleUser);

    /**
     * 查询已分配角色的用户
     *
     * @param roleUser 角色用户
     * @return 列表
     */
    List<RoleUser> queryDistributionRole(@Param("roleUser") RoleUser roleUser);

    /**
     * 批量删除
     *
     * @param roleId 角色id userId 用户列表
     * @return 列表
     */
    int deleteByIdList(@Param("roleId") Long roleId, @Param("userIds") List<Long> userIds);

    /**
     * 查询已分配角色的用户数量
     *
     * @param roleUser 角色用户
     * @return 数量
     */
    long queryDistributionRoleCount(@Param("roleUser")RoleUser roleUser);

    /**
     * 通过角色id查询
     *
     * @param roleId 角色id
     * @return 列表
     */
    List<SysUserRole> queryByRoleId(Long roleId);
}

