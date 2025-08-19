package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.BannerDTO;
import com.safewind.application.controller.dto.BannerQueryDTO;
import com.safewind.application.controller.vo.BannerVO;
import com.safewind.domain.bo.BannerBO;
import com.safewind.domain.bo.BannerListBO;
import com.safewind.domain.bo.BannerQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-19  00:00:00
 * @Description: 横幅转换器
 */
@Mapper
public interface BannerConverter {

    BannerConverter INSTANCE = Mappers.getMapper(BannerConverter.class);

    /**
     * BO转VO
     */
    BannerVO boToVO(BannerBO bannerBO);

    /**
     * BO列表转VO列表
     */
    List<BannerVO> boListToVOList(List<BannerBO> bannerBOList);

    /**
     * 列表BO转VO
     */
    BannerVO listBOToVO(BannerListBO bannerListBO);

    /**
     * 列表BO列表转VO列表
     */
    List<BannerVO> listBOListToVOList(List<BannerListBO> bannerListBOList);

    /**
     * DTO转BO
     */
    BannerBO dtoToBO(BannerDTO bannerDTO);

    /**
     * 查询DTO转查询BO
     */
    BannerQueryBO queryDTOToQueryBO(BannerQueryDTO queryDTO);
}