package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.MenuBO;
import com.safewind.domain.bo.MenuListBO;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  18:59
 * @Description: 菜单领域层
 */
public interface MenuDomainService {
    // 添加菜单
    MenuBO addMenu(MenuBO menuBO);
    // 修改菜单
    Boolean updateMenu(MenuBO menuBO);
    // 删除菜单
    Boolean deleteMenu(Long menuId);
    // 查询菜单（模糊查询）
    PageResult<MenuListBO> queryMenu(MenuListBO menuListBO);
    // 查询树状菜单
    List<MenuListBO> queryMenuTree(MenuListBO menuListBO);
}
