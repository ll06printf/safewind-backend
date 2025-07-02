package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.Page;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.SysMenu;
import com.safewind.infra.basic.dao.SysMenuDao;
import com.safewind.infra.basic.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuDao sysMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public SysMenu queryById(Long menuId) {
        return this.sysMenuDao.queryById(menuId);
    }


    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysMenu insert(SysMenu sysMenu) {
        this.sysMenuDao.insert(sysMenu);
        return sysMenu;
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysMenu update(SysMenu sysMenu) {
        this.sysMenuDao.update(sysMenu);
        return this.queryById(sysMenu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long menuId) {
        return this.sysMenuDao.deleteById(menuId) > 0;
    }

    /**
     * @param: roleId
     * @return SysMenu
     * @author Darven
     * @date 2025/6/27 15:38
     * @description: 通过角色查询菜单
     */
    @Override
    public SysMenu queryByRole(Long roleId) {
        return this.sysMenuDao.queryByRoleId(roleId);
    }

    /**
     * @param: sysMenu
     * @param: page
     * @return List<SysMenu>
     * @author Darven
     * @date 2025/6/27 16:38
     * @description:
     */
    @Override
    public List<SysMenu> queryByMenu(SysMenu sysMenu, Page page) {
        // 构造
        Long offset = PageUtils.getOffset(page.getPageNum(), page.getPageSize());
        page.setPageNum(offset);
        return this.sysMenuDao.queryAllByLimit(sysMenu,page);
    }

    /**
     * @return Long
     * @author Darven
     * @date 2025/6/27 17:53
     * @description: 统计数量
     */
    @Override
    public Long count() {
        return this.sysMenuDao.count(new SysMenu());
    }

    /**
     * @return List<SysMenu>
     * @author Darven
     * @date 2025/6/27 18:04
     * @description: 查询所有菜单
     */
    @Override
    public List<SysMenu> query(SysMenu sysMenu) {
        return this.sysMenuDao.query(sysMenu);
    }
}
