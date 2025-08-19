package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.BannerBO;
import com.safewind.domain.bo.BannerListBO;
import com.safewind.domain.bo.BannerQueryBO;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-01-01  00:00:00
 * @description: 横幅业务模块
 */
public interface BannerDomainService {
    /**
     * 分页查询横幅列表
     */
    PageResult<BannerBO> queryBannerPage(BannerQueryBO queryBO);

    /**
     * 根据ID查询横幅详情
     */
    BannerBO getBannerById(Long id);

    /**
     * 新增横幅
     */
    BannerBO addBanner(BannerBO bannerBO);

    /**
     * 更新横幅
     */
    BannerBO updateBanner(BannerBO bannerBO);

    /**
     * 删除横幅
     */
    Boolean deleteBanner(Long id);

    /**
     * 获取所有横幅列表
     */
    List<BannerListBO> getAllBanners();
}