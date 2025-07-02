package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.infra.basic.entity.WsNews;
import com.safewind.infra.basic.dao.WsNewsDao;
import com.safewind.infra.basic.service.WsNewsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 新闻(WsNews)表服务实现类
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
@Service("wsNewsService")
public class WsNewsServiceImpl implements WsNewsService {
    @Resource
    private WsNewsDao wsNewsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WsNews queryById(Long id) {
        return this.wsNewsDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param wsNews 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsNews insert(WsNews wsNews) {
        this.wsNewsDao.insert(wsNews);
        return wsNews;
    }

    /**
     * 修改数据
     *
     * @param wsNews 实例对象
     * @return 实例对象
     */
    @EntityFill
    @Override
    public WsNews update(WsNews wsNews) {
        this.wsNewsDao.update(wsNews);
        return this.queryById(wsNews.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.wsNewsDao.deleteById(id) > 0;
    }
}
