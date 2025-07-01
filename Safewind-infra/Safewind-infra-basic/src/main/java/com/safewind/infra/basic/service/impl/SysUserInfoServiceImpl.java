package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysUserInfo;
import com.safewind.infra.basic.dao.SysUserInfoDao;
import com.safewind.infra.basic.service.SysUserInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 成员信息表(SysUserInfo)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysUserInfoService")
public class SysUserInfoServiceImpl implements SysUserInfoService {
    @Resource
    private SysUserInfoDao sysUserInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserInfo queryById(Long id) {
        return this.sysUserInfoDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysUserInfo insert(SysUserInfo sysUserInfo) {
        this.sysUserInfoDao.insert(sysUserInfo);
        return sysUserInfo;
    }

    /**
     * 修改数据
     *
     * @param sysUserInfo 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysUserInfo update(SysUserInfo sysUserInfo) {
        this.sysUserInfoDao.update(sysUserInfo);
        return this.queryById(sysUserInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserInfoDao.deleteById(id) > 0;
    }
}
