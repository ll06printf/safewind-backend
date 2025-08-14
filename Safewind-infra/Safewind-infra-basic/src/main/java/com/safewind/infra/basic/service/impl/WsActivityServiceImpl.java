package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.WsActivity;
import com.safewind.infra.basic.dao.WsActivityDao;
import com.safewind.infra.basic.entity.WsQueryActivity;
import com.safewind.infra.basic.service.WsActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请表(WsActivity)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("wsActivityService")
public class WsActivityServiceImpl implements WsActivityService {
    @Resource
    private WsActivityDao wsActivityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WsActivity queryById(Long id) {
        return this.wsActivityDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param wsActivity 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsActivity insert(WsActivity wsActivity) {
        this.wsActivityDao.insert(wsActivity);
        return wsActivity;
    }

    /**
     * 修改数据
     *
     * @param wsActivity 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsActivity update(WsActivity wsActivity) {
        this.wsActivityDao.update(wsActivity);
        return this.queryById(wsActivity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.wsActivityDao.deleteById(id) > 0;
    }

    /**
     * 分页查询活动列表
     *
     * @param query 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @Override
    public PageResult<WsActivity> queryPage(WsQueryActivity query, Long pageNum, Long pageSize) {
        long total = this.wsActivityDao.countActivity(query);
        List<WsActivity> list = this.wsActivityDao.queryAllByLimit(query, PageUtils.getOffset(pageNum, pageSize), pageSize);
        return PageResult.<WsActivity>builder()
                .data(list)
                .totalSize(total)
                .totalPages(PageUtils.getTotalPage(total, pageSize))
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }

    /**
     * 获取最新活动列表
     *
     * @param limit 限制数量
     * @return 最新活动列表
     */
    @Override
    public List<WsActivity> getLatestActivities(Integer limit) {
        return this.wsActivityDao.getLatestActivities(limit);
    }

}
