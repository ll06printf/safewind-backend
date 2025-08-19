package com.safewind.domain.service.impl;

import com.safewind.common.enums.NewsExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.NewsBO;
import com.safewind.domain.bo.NewsListBO;
import com.safewind.domain.bo.NewsQueryBO;
import com.safewind.domain.converter.NewsDomainConverter;
import com.safewind.domain.markdown.MarkdownHelper;
import com.safewind.domain.service.NewsDomainService;
import com.safewind.infra.basic.entity.WsNews;
import com.safewind.infra.basic.entity.WsNewsQuery;
import com.safewind.infra.basic.service.WsNewsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:24
 * @description: 新闻业务模块实现
 */
@Service
public class NewsDomainServiceImpl implements NewsDomainService {

    @Resource
    private WsNewsService wsNewsService;

    @Override
    public PageResult<NewsBO> queryNewsPage(NewsQueryBO queryBO) {
        // 构建查询条件
        WsNewsQuery query = new WsNewsQuery();
        query.setTitle(queryBO.getTitle());
        query.setStartTime(queryBO.getStartTime());
        query.setEndTime(queryBO.getEndTime());

        // 调用基础设施层服务
        PageResult<WsNews> pageResult = wsNewsService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());

        // 转换为领域对象
        List<NewsBO> newsBOList = NewsDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        return PageResult.<NewsBO>builder()
                .data(newsBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public NewsBO getNewsById(Long id) {
        WsNews wsNews = wsNewsService.queryById(id);
        if (wsNews == null) {
            return null;
        }
        return NewsDomainConverter.INSTANCE.entityToBO(wsNews);
    }

    @Override
    public NewsBO addNews(NewsBO newsBO) {
        WsNews wsNews = NewsDomainConverter.INSTANCE.boToEntity(newsBO);
        WsNews savedNews = wsNewsService.insert(wsNews);
        return NewsDomainConverter.INSTANCE.entityToBO(savedNews);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public NewsBO updateNews(NewsBO newsBO) {
        WsNews wsNews = NewsDomainConverter.INSTANCE.boToEntity(newsBO);
        // 检查新闻是否存在
        WsNews wsNewsToUpdate = wsNewsService.queryById(wsNews.getId());
        if (wsNewsToUpdate == null) {
            throw new BizException(NewsExceptionEnum.NEWS_IS_NULL);
        }
        WsNews updatedNews = wsNewsService.update(wsNews);
        return NewsDomainConverter.INSTANCE.entityToBO(updatedNews);
    }

    @Override
    public Boolean deleteNews(Long id) {
        // 检查是否存在
        WsNews wsNews = wsNewsService.queryById(id);
        if(Objects.isNull(wsNews)){
            throw new BizException(NewsExceptionEnum.NEWS_IS_NULL);
        }
        // 软删除
        return wsNewsService.deleteNews(id);
    }

    @Override
    public List<NewsListBO> getLatestNews(Long limit) {
        List<WsNews> latestNews = wsNewsService.getLatestNews(limit);
        // 转化 MD
        latestNews.forEach(news -> news.setHtmlContent(MarkdownHelper.convertMarkdown2Html(news.getHtmlContent())));
        return NewsDomainConverter.INSTANCE.entityListToListBOList(latestNews);
    }

    @Override
    public PageResult<NewsBO> queryWsNewsPage(NewsQueryBO queryBO) {
        // 构建查询条件
        WsNewsQuery query = new WsNewsQuery();
        query.setTitle(queryBO.getTitle());
        query.setStartTime(queryBO.getStartTime());
        query.setEndTime(queryBO.getEndTime());

        // 调用基础设施层服务
        PageResult<WsNews> pageResult = wsNewsService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());

        // 转换为领域对象
        List<NewsBO> newsBOList = NewsDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());
        // 转换 MD
        newsBOList.forEach(newsBO -> newsBO.setHtmlContent(MarkdownHelper.convertMarkdown2Html(newsBO.getHtmlContent())));

        return PageResult.<NewsBO>builder()
                .data(newsBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public NewsBO getWsNewsById(Long id) {
        WsNews wsNews = wsNewsService.queryById(id);
        if (wsNews == null) {
            return null;
        }
        wsNews.setHtmlContent(MarkdownHelper.convertMarkdown2Html(wsNews.getHtmlContent()));
        return NewsDomainConverter.INSTANCE.entityToBO(wsNews);
    }
}
