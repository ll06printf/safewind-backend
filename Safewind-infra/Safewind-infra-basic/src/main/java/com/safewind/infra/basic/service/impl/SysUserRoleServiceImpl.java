package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.RoleUser;
import com.safewind.infra.basic.entity.SysUserRole;
import com.safewind.infra.basic.dao.SysUserRoleDao;
import com.safewind.infra.basic.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.sysUserRoleDao.deleteById(roleId) > 0;
    }

    /**
     * 批量插入数据
     *
     * @param sysUserRoleList 数据列表
     * @return 影响行数
     */
    @EntityFill // 填充公用属性
    @Override
    public long insertBatch(List<SysUserRole> sysUserRoleList) {
        int i = this.sysUserRoleDao.insertBatch(sysUserRoleList);
        return i;
    }

    @Override
    public List<RoleUser> queryUnDistributionRole(RoleUser roleUser) {
        roleUser.setPageNum(PageUtils.getOffset(roleUser.getPageNum(), roleUser.getPageSize()));
        return this.sysUserRoleDao.queryUnDistributionRole(roleUser);
    }

    @Override
    public long count(SysUserRole sysUserRole) {
        return this.sysUserRoleDao.count(sysUserRole);
    }

    @Override
    public long queryUnDistributionRoleCount(RoleUser roleUser) {
        return this.sysUserRoleDao.queryUnDistributionRoleCount(roleUser);
    }

    @Override
    public List<RoleUser> queryDistributionRole(RoleUser roleUser) {
        roleUser.setPageNum(PageUtils.getOffset(roleUser.getPageNum(), roleUser.getPageSize()));
        return this.sysUserRoleDao.queryDistributionRole(roleUser);
    }

    @Override
    public boolean deleteByRoleUserId(Long roleId, List<Long> userIds) {
        return this.sysUserRoleDao.deleteByIdList(roleId,userIds)==userIds.size();
    }

    @Override
    public long queryDistributionRoleCount(RoleUser roleUser) {
        return this.sysUserRoleDao.queryDistributionRoleCount(roleUser);
    }
}
