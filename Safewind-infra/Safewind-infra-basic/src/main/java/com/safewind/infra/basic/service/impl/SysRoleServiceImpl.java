package com.safewind.infra.basic.service.impl;

import com.safewind.common.page.Page;
import com.safewind.common.page.PageUtils;
import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysRole;
import com.safewind.infra.basic.dao.SysRoleDao;
import com.safewind.infra.basic.entity.SysUserRole;
import com.safewind.infra.basic.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Long roleId) {
        return this.sysRoleDao.queryById(roleId);
    }


    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleDao.insert(sysRole);
        return sysRole;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysRole update(SysRole sysRole) {
        this.sysRoleDao.update(sysRole);
        return this.queryById(sysRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.sysRoleDao.deleteById(roleId) > 0;
    }

    @Override
    public List<SysRole> queryRole(SysRole sysRole, Page page) {
        // 设置分页参数，偏移量
        page.setPageNum(PageUtils.getOffset(page.getPageNum(), page.getPageSize()));
        return this.sysRoleDao.queryRole(sysRole, page);
    }

    @Override
    public long count() {
        return this.sysRoleDao.count(new SysRole());
    }
}
