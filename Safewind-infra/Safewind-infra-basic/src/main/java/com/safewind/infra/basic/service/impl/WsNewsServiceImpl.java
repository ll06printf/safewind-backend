package com.safewind.infra.basic.service.impl;

import com.safewind.common.annotation.EntityFill;
import com.safewind.common.enums.CommonStatusEnum;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.infra.basic.entity.WsNews;
import com.safewind.infra.basic.dao.WsNewsDao;
import com.safewind.infra.basic.entity.WsNewsQuery;
import com.safewind.infra.basic.service.WsNewsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @Override
    public PageResult<WsNews> queryPage(WsNewsQuery query, Long pageNum, Long pageSize) {
        // 计算偏移量
        Long offset = PageUtils.getOffset(pageNum, pageSize);
        // 查询数据
        List<WsNews> data =this.wsNewsDao.queryPage(query,offset,pageSize);
        // 查询总数
        Long total = this.wsNewsDao.countNews(query,offset,pageSize);
        // 封装结果并返回
        return PageResult.<WsNews>builder()
                .data(data)
                .totalSize(total)
                .totalPages(PageUtils.getTotalPage(total, pageSize))
                .pageNum(pageNum)
                .pageSize(pageSize)
                .build();
    }

    @Override
    public List<WsNews> getLatestNews(Long limit) {
        // 获取最新新闻
        return this.wsNewsDao.queryPage(new WsNewsQuery(), 0L, limit);
    }

    @Override
    public Boolean deleteNews(Long id) {
        // 软删除
        WsNews wsNews = new WsNews();
        wsNews.setId(id);
        wsNews.setDelFlag(CommonStatusEnum.DELETE_STATUS.getStatus());
        return this.wsNewsDao.update(wsNews) > 0;
    }
}
