package com.safewind.domain.service.impl;

import com.safewind.common.enums.BannerExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.BannerBO;
import com.safewind.domain.bo.BannerListBO;
import com.safewind.domain.bo.BannerQueryBO;
import com.safewind.domain.converter.BannerDomainConverter;
import com.safewind.domain.service.BannerDomainService;
import com.safewind.infra.basic.entity.WsBanner;
import com.safewind.infra.basic.entity.WsBannerQuery;
import com.safewind.infra.basic.service.WsBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅业务模块实现
 */
@Service
public class BannerDomainServiceImpl implements BannerDomainService {

    @Autowired
    private WsBannerService wsBannerService;

    @Override
    public PageResult<BannerBO> queryBannerPage(BannerQueryBO queryBO) {
        // 参数校验
        if (Objects.isNull(queryBO)) {
            throw new BizException(BannerExceptionEnum.ID_ERROR);
        }

        // 构建查询条件
        WsBannerQuery query = new WsBannerQuery();
        query.setTitle(queryBO.getTitle());
        query.setSubTitle(queryBO.getSubTitle());

        // 分页查询
        PageResult<WsBanner> pageResult = wsBannerService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());

        // 转换结果
        List<BannerBO> bannerBOList = BannerDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        return PageResult.<BannerBO>builder()
                .data(bannerBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public BannerBO getBannerById(Long id) {
        // 参数校验
        if (Objects.isNull(id)) {
            throw new BizException(BannerExceptionEnum.ID_NOT_NULL);
        }

        // 查询横幅
        WsBanner wsBanner = wsBannerService.queryById(id);
        if (Objects.isNull(wsBanner)) {
            throw new BizException(BannerExceptionEnum.BANNER_IS_NULL);
        }

        // 转换结果
        return BannerDomainConverter.INSTANCE.entityToBO(wsBanner);
    }

    @Override
    public BannerBO addBanner(BannerBO bannerBO) {
        // 参数校验
        if (Objects.isNull(bannerBO)) {
            throw new BizException(BannerExceptionEnum.ID_ERROR);
        }
        if (Objects.isNull(bannerBO.getTitle()) || bannerBO.getTitle().trim().isEmpty()) {
            throw new BizException(BannerExceptionEnum.TITLE_NOT_NULL);
        }
        if (Objects.isNull(bannerBO.getSubTitle()) || bannerBO.getSubTitle().trim().isEmpty()) {
            throw new BizException(BannerExceptionEnum.SUB_TITLE_NOT_NULL);
        }
        if (Objects.isNull(bannerBO.getBackgroundPicture()) || bannerBO.getBackgroundPicture().trim().isEmpty()) {
            throw new BizException(BannerExceptionEnum.BACKGROUND_PICTURE_NOT_NULL);
        }

        // 转换实体
        WsBanner wsBanner = BannerDomainConverter.INSTANCE.bannerBOToEntity(bannerBO);

        // 保存横幅
        WsBanner savedBanner = wsBannerService.insert(wsBanner);

        // 转换结果
        return BannerDomainConverter.INSTANCE.entityToBO(savedBanner);
    }

    @Override
    public BannerBO updateBanner(BannerBO bannerBO) {
        // 参数校验
        if (Objects.isNull(bannerBO) || Objects.isNull(bannerBO.getId())) {
            throw new BizException(BannerExceptionEnum.ID_NOT_NULL);
        }
        if (Objects.isNull(bannerBO.getTitle()) || bannerBO.getTitle().trim().isEmpty()) {
            throw new BizException(BannerExceptionEnum.TITLE_NOT_NULL);
        }
        if (Objects.isNull(bannerBO.getSubTitle()) || bannerBO.getSubTitle().trim().isEmpty()) {
            throw new BizException(BannerExceptionEnum.SUB_TITLE_NOT_NULL);
        }
        if (Objects.isNull(bannerBO.getBackgroundPicture()) || bannerBO.getBackgroundPicture().trim().isEmpty()) {
            throw new BizException(BannerExceptionEnum.BACKGROUND_PICTURE_NOT_NULL);
        }

        // 检查横幅是否存在
        WsBanner existingBanner = wsBannerService.queryById(bannerBO.getId());
        if (Objects.isNull(existingBanner)) {
            throw new BizException(BannerExceptionEnum.BANNER_IS_NULL);
        }

        // 转换实体
        WsBanner wsBanner = BannerDomainConverter.INSTANCE.bannerBOToEntity(bannerBO);

        // 更新横幅
        WsBanner updatedBanner = wsBannerService.update(wsBanner);

        // 转换结果
        return BannerDomainConverter.INSTANCE.entityToBO(updatedBanner);
    }

    @Override
    public Boolean deleteBanner(Long id) {
        // 参数校验
        if (Objects.isNull(id)) {
            throw new BizException(BannerExceptionEnum.ID_NOT_NULL);
        }

        // 检查横幅是否存在
        WsBanner existingBanner = wsBannerService.queryById(id);
        if (Objects.isNull(existingBanner)) {
            throw new BizException(BannerExceptionEnum.BANNER_IS_NULL);
        }

        // 删除横幅
        return wsBannerService.deleteById(id);
    }

    @Override
    public List<BannerListBO> getAllBanners() {
        // 查询所有横幅
        List<WsBanner> wsBannerList = wsBannerService.queryAll();

        // 转换结果
        return BannerDomainConverter.INSTANCE.entityListToListBOList(wsBannerList);
    }
} 