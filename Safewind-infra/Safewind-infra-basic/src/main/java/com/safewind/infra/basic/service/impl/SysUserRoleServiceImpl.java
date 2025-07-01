package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysUserRole;
import com.safewind.infra.basic.dao.SysUserRoleDao;
import com.safewind.infra.basic.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole queryById(Long userId) {
        return this.sysUserRoleDao.queryById(userId);
    }


    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysUserRole insert(SysUserRole sysUserRole) {
        this.sysUserRoleDao.insert(sysUserRole);
        return sysUserRole;
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysUserRole update(SysUserRole sysUserRole) {
        this.sysUserRoleDao.update(sysUserRole);
        return this.queryById(sysUserRole.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.sysUserRoleDao.deleteById(userId) > 0;
    }
}
