package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysPosts;
import com.safewind.infra.basic.dao.SysPostsDao;
import com.safewind.infra.basic.service.SysPostsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * (SysPosts)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysPostsService")
public class SysPostsServiceImpl implements SysPostsService {
    @Resource
    private SysPostsDao sysPostsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysPosts queryById(Long id) {
        return this.sysPostsDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param sysPosts 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysPosts insert(SysPosts sysPosts) {
        this.sysPostsDao.insert(sysPosts);
        return sysPosts;
    }

    /**
     * 修改数据
     *
     * @param sysPosts 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysPosts update(SysPosts sysPosts) {
        this.sysPostsDao.update(sysPosts);
        return this.queryById(sysPosts.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysPostsDao.deleteById(id) > 0;
    }
}
