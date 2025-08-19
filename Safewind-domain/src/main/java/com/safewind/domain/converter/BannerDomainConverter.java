package com.safewind.domain.converter;

import com.safewind.domain.bo.BannerBO;
import com.safewind.domain.bo.BannerListBO;
import com.safewind.infra.basic.entity.WsBanner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-19  00:00:00
 * @Description: 横幅领域层转换器
 */
@Mapper
public interface BannerDomainConverter {
    BannerDomainConverter INSTANCE = Mappers.getMapper(BannerDomainConverter.class);

    /**
     * BO转实体
     */
    WsBanner bannerBOToEntity(BannerBO bannerBO);

    /**
     * 实体转BO
     */
    BannerBO entityToBO(WsBanner wsBanner);

    /**
     * 实体列表转BO列表
     */
    List<BannerBO> entityListToBOList(List<WsBanner> wsBannerList);

    /**
     * 实体转列表BO
     */
    BannerListBO entityToListBO(WsBanner wsBanner);

    /**
     * 实体列表转列表BO列表
     */
    List<BannerListBO> entityListToListBOList(List<WsBanner> wsBannerList);
}