package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.SysReview;
import com.safewind.infra.basic.dao.SysReviewDao;
import com.safewind.infra.basic.service.SysReviewService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * (SysReview)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("sysReviewService")
public class SysReviewServiceImpl implements SysReviewService {
    @Resource
    private SysReviewDao sysReviewDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysReview queryById(Long id) {
        return this.sysReviewDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param sysReview 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysReview insert(SysReview sysReview) {
        this.sysReviewDao.insert(sysReview);
        return sysReview;
    }

    /**
     * 修改数据
     *
     * @param sysReview 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public SysReview update(SysReview sysReview) {
        this.sysReviewDao.update(sysReview);
        return this.queryById(sysReview.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysReviewDao.deleteById(id) > 0;
    }
}
