package com.safewind.infra.basic.service;

import com.safewind.infra.basic.entity.SysReview;

/**
 * (SysReview)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysReviewService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysReview queryById(Long id);


    /**
     * 新增数据
     *
     * @param sysReview 实例对象
     * @return 实例对象
     */
    SysReview insert(SysReview sysReview);

    /**
     * 修改数据
     *
     * @param sysReview 实例对象
     * @return 实例对象
     */
    SysReview update(SysReview sysReview);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    /**
     * 逻辑删除数据
     *
     * @param id 主键
     *
     * @return 是否成功
     */
    boolean softDeleteById(Long id);

    /**
     * 通过申请ID查询单条数据
     *
     * @param id 申请ID
     *           @param status 状态
     * @return 实例对象
     */
    SysReview queryByApplyId(Long id,Integer status);
}
