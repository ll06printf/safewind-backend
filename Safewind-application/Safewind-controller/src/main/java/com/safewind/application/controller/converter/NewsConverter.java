package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.NewsDTO;
import com.safewind.application.controller.dto.NewsQueryDTO;
import com.safewind.application.controller.vo.NewsVO;
import com.safewind.domain.bo.NewsBO;
import com.safewind.domain.bo.NewsListBO;
import com.safewind.domain.bo.NewsQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-11  23:30
 * @Description: 新闻转换器
 */
@Mapper
public interface NewsConverter {

    NewsConverter INSTANCE = Mappers.getMapper(NewsConverter.class);

    /**
     * BO转VO
     */
    NewsVO boToVO(NewsBO newsBO);

    /**
     * BO列表转VO列表
     */
    List<NewsVO> boListToVOList(List<NewsBO> newsBOList);

    /**
     * 列表BO转VO
     */
    NewsVO listBOToVO(NewsListBO newsListBO);

    /**
     * 列表BO列表转VO列表
     */
    List<NewsVO> listBOListToVOList(List<NewsListBO> newsListBOList);

    /**
     * DTO转BO
     */
    NewsBO dtoToBO(NewsDTO newsDTO);

    /**
     * 查询DTO转查询BO
     */
    NewsQueryBO queryDTOToQueryBO(NewsQueryDTO queryDTO);
}
