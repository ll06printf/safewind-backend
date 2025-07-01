package com.safewind.domain.service.impl;

import com.safewind.common.page.Page;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.domain.bo.MenuBO;
import com.safewind.domain.bo.MenuListBO;
import com.safewind.domain.converter.MenuDomainConverter;
import com.safewind.domain.service.MenuDomainService;
import com.safewind.infra.basic.entity.SysMenu;
import com.safewind.infra.basic.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  18:58
 * @Description: 菜单领域实
 */
@Service
public class MenuDomainServiceImpl implements MenuDomainService {

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public MenuBO addMenu(MenuBO menuBO) {
        SysMenu sysMenu = MenuDomainConverter.INSTANCE.menuBOToEntity(menuBO);
        SysMenu insert = sysMenuService.insert(sysMenu);
        return MenuDomainConverter.INSTANCE.entityToBO(insert);
    }

    /**
     * @return Boolean
     * @param: menuBO
     * @author Darven
     * @date 2025/6/27 12:54
     * @description: 修改菜单
     */
    @Override
    public Boolean updateMenu(MenuBO menuBO) {
        SysMenu sysMenu = MenuDomainConverter.INSTANCE.menuBOToEntity(menuBO);
        return !Objects.isNull(sysMenuService.update(sysMenu));
    }

    /**
     * @return Boolean
     * @param: menuId
     * @author Darven
     * @date 2025/6/27 13:08
     * @description: 删除菜单
     */
    @Override
    public Boolean deleteMenu(Long menuId) {
        return sysMenuService.deleteById(menuId);
    }

    /**
     * @return List<MenuListBO>
     * @param: menuListBO
     * @author Darven
     * @date 2025/6/27 14:13
     * @description: 查询菜单列表
     * -将数据查询到内存，在内存中统一处理，不用多次查询
     */
    @Override
    public PageResult<MenuListBO> queryMenu(MenuListBO menuListBO) {
        SysMenu sysMenu = MenuDomainConverter.INSTANCE.menuListBOToEntity(menuListBO);
        // 提取出page，传入基础设施层
        Page page = new Page();
        page.setPageNum(menuListBO.getPageNum());
        page.setPageSize(menuListBO.getPageSize());
        List<SysMenu> sysMenuList = sysMenuService.queryByMenu(sysMenu, page);

        // 进行转换
        List<MenuListBO> menuListBOList = MenuDomainConverter.INSTANCE.entityToListBO(sysMenuList);
        // 查询总页数
        Long total = sysMenuService.count();

        // 对菜单进行分页封装
        PageResult<MenuListBO> menuListBOPageResult = new PageResult<>();
        menuListBOPageResult.setPageNum(menuListBO.getPageNum());
        menuListBOPageResult.setPageSize(menuListBO.getPageSize());
        menuListBOPageResult.setTotalPages(PageUtils.getTotalPage(total, menuListBO.getPageSize()));
        menuListBOPageResult.setData(menuListBOList);
        menuListBOPageResult.setTotalSize(total);
        return menuListBOPageResult;
    }

    /**
     * @return List<MenuListBO>
     * @author Darven
     * @date 2025/6/27 18:06
     * @description: 查询所有菜单，然后返回菜单树
     */
    @Override
    public List<MenuListBO> queryMenuTree(MenuListBO menuListBO) {
        // 封装查询条件
        SysMenu sysMenu = new SysMenu();
        sysMenu.setStatus(menuListBO.getStatus());
        sysMenu.setMenuName(menuListBO.getMenuName());
        List<SysMenu> sysMenuList = sysMenuService.query(sysMenu);
        // 转换
        List<MenuListBO> menuListBOList = MenuDomainConverter.INSTANCE.entityToListBO(sysMenuList);
        // 封装成菜单树
        return buildTree(menuListBOList);
    }

    /**
     * @return List<MenuListBO>
     * @param: menuListBOList
     * @author Darven
     * @date 2025/6/27 17:26
     * @description: 构建菜单树
     */
    private List<MenuListBO> buildTree(List<MenuListBO> menuListBOList) {
        List<MenuListBO> list = new ArrayList<>();
        // 获取map
        Map<Long, MenuListBO> menuListBOMap = menuListBOList.stream()
                .collect(Collectors.toMap(MenuListBO::getMenuId, m -> m));

        // 遍历组装树
        for (MenuListBO b : menuListBOList) {
            Long parentId = b.getParentId();

            // 子节点
            if (parentId != null && parentId != 0) {
                MenuListBO parentMenu = menuListBOMap.get(parentId);
                if (parentMenu != null) {
                    if (parentMenu.getMenuListBOList() == null) {
                        List<MenuListBO> childList = new ArrayList<>();
                        childList.add(b);
                        parentMenu.setMenuListBOList(childList);
                    } else {
                        parentMenu.getMenuListBOList().add(b);
                    }
                } else {
                    // 如果找不到父节点，也作为根节点加入
                    list.add(b);
                }
            } else {
                // parentId == null 或 == 0 的情况，也可以统一处理或保留原意
                list.add(b);
            }
        }

        return list;
    }

}
