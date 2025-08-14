package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.dao.WsWallDao;
import com.safewind.infra.basic.entity.WsWall;
import com.safewind.infra.basic.entity.WsQueryWall;
import com.safewind.infra.basic.service.WsWallService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 海风墙(WsWall)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("wsWallService")
public class WsWallServiceImpl implements WsWallService {

    private final WsWallDao wsWallDao;

    public WsWallServiceImpl(WsWallDao wsWallDao) {
        this.wsWallDao = wsWallDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WsWall queryById(Long id) {
        return this.wsWallDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param wsWall 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsWall insert(WsWall wsWall) {
        this.wsWallDao.insert(wsWall);
        return wsWall;
    }

    /**
     * 修改数据
     *
     * @param wsWall 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsWall update(WsWall wsWall) {
        this.wsWallDao.update(wsWall);
        return this.queryById(wsWall.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.wsWallDao.deleteById(id) > 0;
    }

    /**
     * 分页查询弹幕列表
     *
     * @param queryWall 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @Override
    public PageResult<WsWall> queryPage(WsQueryWall queryWall, Long pageNum, Long pageSize) {
        List<WsWall> data = this.wsWallDao.queryPage(queryWall, PageUtils.getOffset(pageNum, pageSize), pageSize);
        long count = this.wsWallDao.count(queryWall);
        return PageResult.<WsWall>builder()
                .data(data)
                .totalSize(count)
                .totalPages(PageUtils.getTotalPage(count, pageSize))
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }

    /**
     * 获取最新弹幕列表
     *
     * @param limit 限制数量
     * @return 最新弹幕列表
     */
    @Override
    public List<WsWall> getLatestWalls(Integer limit) {
        return this.wsWallDao.queryByLimit(limit);
    }

    @Override
    public long count(WsQueryWall query) {
        return this.wsWallDao.count(query);
    }
}
