package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysUserDept;
import com.safewind.infra.basic.dao.SysUserDeptDao;
import com.safewind.infra.basic.service.SysUserDeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户部门映射表(SysUserDept)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysUserDeptService")
public class SysUserDeptServiceImpl implements SysUserDeptService {
    @Resource
    private SysUserDeptDao sysUserDeptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUserDept queryById(Long id) {
        return this.sysUserDeptDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param sysUserDept 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysUserDept insert(SysUserDept sysUserDept) {
        this.sysUserDeptDao.insert(sysUserDept);
        return sysUserDept;
    }

    /**
     * 修改数据
     *
     * @param sysUserDept 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysUserDept update(SysUserDept sysUserDept) {
        this.sysUserDeptDao.update(sysUserDept);
        return this.queryById(sysUserDept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDeptDao.deleteById(id) > 0;
    }
}
