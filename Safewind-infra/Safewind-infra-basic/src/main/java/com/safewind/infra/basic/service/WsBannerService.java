package com.safewind.infra.basic.service;

import com.safewind.common.page.PageResult;
import com.safewind.infra.basic.entity.WsBanner;
import com.safewind.infra.basic.entity.WsBannerQuery;

import java.util.List;

/**
 * 横幅(WsBanner)表服务接口
 *
 * @author Darven
 * @since 2025-01-01 00:00:00
 */
public interface WsBannerService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WsBanner queryById(Long id);

    /**
     * 新增数据
     *
     * @param wsBanner 实例对象
     * @return 实例对象
     */
    WsBanner insert(WsBanner wsBanner);

    /**
     * 修改数据
     *
     * @param wsBanner 实例对象
     * @return 实例对象
     */
    WsBanner update(WsBanner wsBanner);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return 分页结果
     */
    PageResult<WsBanner> queryPage(WsBannerQuery query, Long pageNum, Long pageSize);

    /**
     * 查询所有横幅
     *
     * @return 横幅列表
     */
    List<WsBanner> queryAll();
}
