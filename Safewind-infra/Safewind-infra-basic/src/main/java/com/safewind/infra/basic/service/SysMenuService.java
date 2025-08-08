package com.safewind.infra.basic.service;

import com.safewind.common.page.Page;
import com.safewind.infra.basic.entity.SysMenu;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    SysMenu queryById(Long menuId);



    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */

    SysMenu insert(SysMenu sysMenu);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    SysMenu update(SysMenu sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

    /**
     * 通过角色查询权限
     *
     * @param roleId 角色ID
     * @return 权限信息
     * */
    List<SysMenu> queryByRole(Long roleId);

    /**
     * 分页查询
     * */
    List<SysMenu> queryByMenu(SysMenu sysMenu, Page page);

    /**
     * 查询总页数
     * */
    Long count();

    List<SysMenu> query(SysMenu sysMenu);
}
