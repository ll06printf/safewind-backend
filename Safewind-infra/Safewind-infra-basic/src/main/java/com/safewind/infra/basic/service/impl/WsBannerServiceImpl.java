package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.WsBanner;
import com.safewind.infra.basic.dao.WsBannerDao;
import com.safewind.infra.basic.entity.WsBannerQuery;
import com.safewind.infra.basic.service.WsBannerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("wsBannerService")
public class WsBannerServiceImpl implements WsBannerService {
    @Resource
    private WsBannerDao wsBannerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WsBanner queryById(Long id) {
        return this.wsBannerDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param wsBanner 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsBanner insert(WsBanner wsBanner) {
        this.wsBannerDao.insert(wsBanner);
        return wsBanner;
    }

    /**
     * 修改数据
     *
     * @param wsBanner 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsBanner update(WsBanner wsBanner) {
        this.wsBannerDao.update(wsBanner);
        return this.queryById(wsBanner.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.wsBannerDao.deleteById(id) > 0;
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return 分页结果
     */
    @Override
    public PageResult<WsBanner> queryPage(WsBannerQuery query, Long pageNum, Long pageSize) {
        long total = this.wsBannerDao.count(new WsBanner());
        if (total > 0) {
            List<WsBanner> list = this.wsBannerDao.queryPage(query, PageUtils.getOffset(pageNum, pageSize), pageSize);
            return PageResult.<WsBanner>builder()
                    .data(list)
                    .totalSize(total)
                    .totalPages(PageUtils.getTotalPage(total, pageSize))
                    .pageNum(pageNum)
                    .pageSize(pageSize)
                    .build();
        }
        return PageResult.<WsBanner>builder()
                .data(List.of())
                .totalSize(0L)
                .totalPages(0L)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }

    /**
     * 查询所有横幅
     *
     * @return 横幅列表
     */
    @Override
    public List<WsBanner> queryAll() {
        return this.wsBannerDao.queryPage(new WsBannerQuery(), 0L, 1000L);
    }
}
