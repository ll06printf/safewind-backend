package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.BannerConverter;
import com.safewind.application.controller.dto.BannerDTO;
import com.safewind.application.controller.dto.BannerQueryDTO;
import com.safewind.application.controller.vo.BannerVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.BannerExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.BannerBO;
import com.safewind.domain.bo.BannerQueryBO;
import com.safewind.domain.service.BannerDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅模块-管理页面
 */
@RestController
@RequestMapping("/admin-banner")
public class BannerAdminController {

    @Autowired
    private BannerDomainService bannerDomainService;

    @ApiOperationLog(description = "横幅列表-分页查询")
    @PostMapping("/getBanner")
    public Result<PageResult<BannerVO>> getBanner(@RequestBody BannerQueryDTO queryDTO) {
        // 类型转化
        BannerQueryBO queryBO = BannerConverter.INSTANCE.queryDTOToQueryBO(queryDTO);
        // 分页查询
        PageResult<BannerBO> pageResult = bannerDomainService.queryBannerPage(queryBO);
        List<BannerVO> voList = BannerConverter.INSTANCE.boListToVOList(pageResult.getData());
        // 封装返回结果
        PageResult<BannerVO> result = PageResult.<BannerVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    @ApiOperationLog(description = "添加横幅")
    @PostMapping("/addBanner")
    public Result<BannerVO> addBanner(@RequestBody BannerDTO bannerDTO) {
        // 类型转化
        BannerBO bannerBO = BannerConverter.INSTANCE.dtoToBO(bannerDTO);
        BannerBO savedBanner = bannerDomainService.addBanner(bannerBO);
        BannerVO bannerVO = BannerConverter.INSTANCE.boToVO(savedBanner);
        return Result.success(bannerVO);
    }

    @ApiOperationLog(description = "修改横幅")
    @PostMapping("/updateBanner")
    public Result<BannerVO> updateBanner(@RequestBody BannerDTO bannerDTO) {
        // 检查参数
        if (bannerDTO.getId() == null) {
            throw new BizException(BannerExceptionEnum.ID_NOT_NULL);
        }
        // 类型转化
        BannerBO bannerBO = BannerConverter.INSTANCE.dtoToBO(bannerDTO);
        BannerBO updatedBanner = bannerDomainService.updateBanner(bannerBO);
        BannerVO bannerVO = BannerConverter.INSTANCE.boToVO(updatedBanner);
        return Result.success(bannerVO);
    }

    @ApiOperationLog(description = "删除横幅")
    @PostMapping("/deleteBanner")
    public Result<Boolean> deleteBanner(@RequestBody BannerDTO bannerDTO) {
        // 检查参数
        if (bannerDTO == null || bannerDTO.getId() == null) {
            throw new BizException(BannerExceptionEnum.ID_NOT_NULL);
        }
        Long id = bannerDTO.getId();
        Boolean result = bannerDomainService.deleteBanner(id);
        return Result.success(result);
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