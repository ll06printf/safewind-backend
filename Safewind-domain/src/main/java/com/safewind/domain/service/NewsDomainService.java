package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.NewsBO;
import com.safewind.domain.bo.NewsListBO;
import com.safewind.domain.bo.NewsQueryBO;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:23
 * @description: 新闻业务模块
 */
public interface NewsDomainService {
    /**
     * 分页查询新闻列表
     */
    PageResult<NewsBO> queryNewsPage(NewsQueryBO queryBO);

    /**
     * 根据ID查询新闻详情
     */
    NewsBO getNewsById(Long id);

    /**
     * 新增新闻
     */
    NewsBO addNews(NewsBO newsBO);

    /**
     * 更新新闻
     */
    NewsBO updateNews(NewsBO newsBO);

    /**
     * 删除新闻
     */
    Boolean deleteNews(Long id);

    /**
     * 获取最新新闻列表
     */
    List<NewsListBO> getLatestNews(Long limit);
}
