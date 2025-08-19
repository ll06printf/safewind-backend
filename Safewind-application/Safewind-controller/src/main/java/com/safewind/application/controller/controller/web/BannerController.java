package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.converter.BannerConverter;
import com.safewind.application.controller.vo.BannerVO;
import com.safewind.common.annotation.Anonymous;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.BannerExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.BannerBO;
import com.safewind.domain.bo.BannerListBO;
import com.safewind.domain.service.BannerDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 官网-横幅模块
 */
@Anonymous
@RestController
@RequestMapping("/ws-banner")
public class BannerController {

    @Autowired
    private BannerDomainService bannerDomainService;

    @ApiOperationLog(description = "获取所有横幅")
    @GetMapping("/getAllBanners")
    public Result<List<BannerVO>> getAllBanners() {
        List<BannerListBO> bannerListBOList = bannerDomainService.getAllBanners();
        List<BannerVO> bannerVOList = BannerConverter.INSTANCE.listBOListToVOList(bannerListBOList);
        return Result.success(bannerVOList);
    }

    @ApiOperationLog(description = "根据ID获取横幅详情")
    @GetMapping("/getBannerById")
    public Result<BannerVO> getBannerById(@RequestParam Long id) {
        // 检查参数
        if (id == null) {
            throw new BizException(BannerExceptionEnum.ID_NOT_NULL);
        }
        BannerBO bannerBO = bannerDomainService.getBannerById(id);
        if (bannerBO == null) {
            throw new BizException(BannerExceptionEnum.BANNER_IS_NULL);
        }
        BannerVO bannerVO = BannerConverter.INSTANCE.boToVO(bannerBO);
        return Result.success(bannerVO);
    }
}