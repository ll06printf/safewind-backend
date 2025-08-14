package com.safewind.infra.basic.service;

import com.safewind.common.page.PageResult;
import com.safewind.infra.basic.entity.WsNews;
import com.safewind.infra.basic.entity.WsNewsQuery;

import java.util.List;

/**
 * 新闻(WsNews)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface WsNewsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WsNews queryById(Long id);


    /**
     * 新增数据
     *
     * @param wsNews 实例对象
     * @return 实例对象
     */
    WsNews insert(WsNews wsNews);

    /**
     * 修改数据
     *
     * @param wsNews 实例对象
     * @return 实例对象
     */
    WsNews update(WsNews wsNews);

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
     * @param pageSize 页大小
     * @return 分页结果
     */
    PageResult<WsNews> queryPage(WsNewsQuery query, Long pageNum, Long pageSize);

    /**
     * 获取最新新闻
     *
     * @param limit 数量
     * @return 新闻列表
     */
    List<WsNews> getLatestNews(Long limit);

    /**
     * 添加新闻
     *
     * @param id 新闻id
     * @return 新闻
     */
    Boolean deleteNews(Long id);
}
