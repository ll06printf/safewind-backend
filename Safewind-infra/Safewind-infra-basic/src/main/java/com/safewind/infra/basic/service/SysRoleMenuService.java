package com.safewind.infra.basic.service;

import com.safewind.infra.basic.entity.SysRoleMenu;

import java.util.List;


/**
 * 角色和菜单关联表(SysRoleMenu)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysRoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    SysRoleMenu queryById(Long roleId);

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    SysRoleMenu insert(SysRoleMenu sysRoleMenu);

    /**
     * 修改数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    SysRoleMenu update(SysRoleMenu sysRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

    /**
     * @param: list
     * @return boolean
     * @author Darven
     * @date 2025/7/1 21:11
     * @description: 批量新增数据（MyBatis原生foreach方法）
     */
    boolean insertBatch(List<SysRoleMenu> list);
}
