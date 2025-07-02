package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysApplyForm;
import com.safewind.infra.basic.dao.SysApplyFormDao;
import com.safewind.infra.basic.service.SysApplyFormService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * 申请表(SysApplyForm)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:27
 */
@Service("sysApplyFormService")
public class SysApplyFormServiceImpl implements SysApplyFormService {
    @Resource
    private SysApplyFormDao sysApplyFormDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysApplyForm queryById(Long id) {
        return this.sysApplyFormDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param sysApplyForm 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysApplyForm insert(SysApplyForm sysApplyForm) {
        this.sysApplyFormDao.insert(sysApplyForm);
        return sysApplyForm;
    }

    /**
     * 修改数据
     *
     * @param sysApplyForm 实例对象
     * @return 实例对象
     */
    @Override
    public SysApplyForm update(SysApplyForm sysApplyForm) {
        this.sysApplyFormDao.update(sysApplyForm);
        return this.queryById(sysApplyForm.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysApplyFormDao.deleteById(id) > 0;
    }
}
