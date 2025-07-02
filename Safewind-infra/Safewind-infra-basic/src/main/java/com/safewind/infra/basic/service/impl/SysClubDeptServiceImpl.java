package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysClubDept;
import com.safewind.infra.basic.dao.SysClubDeptDao;
import com.safewind.infra.basic.service.SysClubDeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * 社团部门映射表(SysClubDept)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
@Service("sysClubDeptService")
public class SysClubDeptServiceImpl implements SysClubDeptService {
    @Resource
    private SysClubDeptDao sysClubDeptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysClubDept queryById(Long id) {
        return this.sysClubDeptDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param sysClubDept 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysClubDept insert(SysClubDept sysClubDept) {
        this.sysClubDeptDao.insert(sysClubDept);
        return sysClubDept;
    }

    /**
     * 修改数据
     *
     * @param sysClubDept 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysClubDept update(SysClubDept sysClubDept) {
        this.sysClubDeptDao.update(sysClubDept);
        return this.queryById(sysClubDept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysClubDeptDao.deleteById(id) > 0;
    }
}
