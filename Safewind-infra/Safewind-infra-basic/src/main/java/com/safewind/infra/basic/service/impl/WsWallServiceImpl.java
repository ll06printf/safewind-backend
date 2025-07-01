package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.WsWall;
import com.safewind.infra.basic.dao.WsWallDao;
import com.safewind.infra.basic.service.WsWallService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 海风墙(WsWall)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("wsWallService")
public class WsWallServiceImpl implements WsWallService {
    @Resource
    private WsWallDao wsWallDao;

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
}
