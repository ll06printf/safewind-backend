package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.WsActivity;
import com.safewind.infra.basic.dao.WsActivityDao;
import com.safewind.infra.basic.service.WsActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
