package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysClub;
import com.safewind.infra.basic.dao.SysClubDao;
import com.safewind.infra.basic.service.SysClubService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 社团表(SysClub)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
@Service("sysClubService")
public class SysClubServiceImpl implements SysClubService {
    @Resource
    private SysClubDao sysClubDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysClub queryById(Long id) {
        return this.sysClubDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param sysClub 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysClub insert(SysClub sysClub) {
        this.sysClubDao.insert(sysClub);
        return sysClub;
    }

    /**
     * 修改数据
     *
     * @param sysClub 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysClub update(SysClub sysClub) {
        this.sysClubDao.update(sysClub);
        return this.queryById(sysClub.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysClubDao.deleteById(id) > 0;
    }
}
