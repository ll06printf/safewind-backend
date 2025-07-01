package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysRoleMenu;
import com.safewind.infra.basic.dao.SysRoleMenuDao;
import com.safewind.infra.basic.service.SysRoleMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public SysRoleMenu queryById(Long roleId) {
        return this.sysRoleMenuDao.queryById(roleId);
    }


    /**
     * 新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysRoleMenu insert(SysRoleMenu sysRoleMenu) {
        this.sysRoleMenuDao.insert(sysRoleMenu);
        return sysRoleMenu;
    }

    /**
     * 修改数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysRoleMenu update(SysRoleMenu sysRoleMenu) {
        this.sysRoleMenuDao.update(sysRoleMenu);
        return this.queryById(sysRoleMenu.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.sysRoleMenuDao.deleteById(roleId) > 0;
    }

    @EntityFill  // todo 一旦涉及插入/修改，并且实体继承BaseEntity，需要添加@EntityFill
    @Override
    public boolean insertBatch(List<SysRoleMenu> list) {
        return this.sysRoleMenuDao.insertBatch(list) > 0;
    }
}
