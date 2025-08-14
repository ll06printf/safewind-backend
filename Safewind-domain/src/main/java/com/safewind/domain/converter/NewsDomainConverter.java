package com.safewind.domain.converter;

import com.safewind.domain.bo.NewsBO;
import com.safewind.domain.bo.NewsListBO;
import com.safewind.infra.basic.entity.WsNews;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:22
 * @description: 领域层新闻转化
 */
@Mapper
public interface NewsDomainConverter {
    NewsDomainConverter INSTANCE = Mappers.getMapper(NewsDomainConverter.class);

    /**
     * 实体转BO
     */
    NewsBO entityToBO(WsNews wsNews);

    /**
     * BO转实体
     */
    WsNews boToEntity(NewsBO newsBO);

    /**
     * 实体列表转BO列表
     */
    List<NewsBO> entityListToBOList(List<WsNews> wsNewsList);

    /**
     * 实体转列表BO
     */
    NewsListBO entityToListBO(WsNews wsNews);

    /**
     * 实体列表转列表BO列表
     */
    List<NewsListBO> entityListToListBOList(List<WsNews> wsNewsList);
}
