package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.ApplyFormConverter;
import com.safewind.application.controller.dto.ApplyFormDTO;
import com.safewind.application.controller.dto.ApplyFormQueryDTO;
import com.safewind.application.controller.dto.ReviewDTO;
import com.safewind.application.controller.vo.ApplyFormVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.ApplyFormExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.ApplyFormBO;
import com.safewind.domain.bo.ApplyFormQueryBO;
import com.safewind.domain.service.ApplyFormDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:32
 * @description: TODO
 */
@RestController
@RequestMapping("/admin-apply")
public class ApplyFormAdminController {

    @Autowired
    private ApplyFormDomainService applyFormDomainService;

    @ApiOperationLog(description = "申请列表-分页查询")
    @PostMapping("/getApplyForm")
    public Result<PageResult<ApplyFormVO>> getApplyForm(@RequestBody ApplyFormQueryDTO queryDTO) {
        // 将 DTO 转换为 BO 对象，用于传递给领域服务
        ApplyFormQueryBO queryBO = ApplyFormConverter.INSTANCE.queryDTOToQueryBO(queryDTO);

        // 调用领域服务进行分页查询，返回分页结果
        PageResult<ApplyFormBO> pageResult = applyFormDomainService.queryApplyFormPage(queryBO);

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<ApplyFormVO> voList = ApplyFormConverter.INSTANCE.boListToVOList(pageResult.getData());

        // 构造分页结果的 VO 对象并返回
        PageResult<ApplyFormVO> result = PageResult.<ApplyFormVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    @ApiOperationLog(description = "提交申请")
    @PostMapping("/submitApply")
    public Result<ApplyFormVO> submitApply(@RequestBody ApplyFormDTO applyFormDTO) {
        // 校验传入的申请数据是否为空
        if (Objects.isNull(applyFormDTO)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_IS_NULL);
        }

        // 将 DTO 转换为 BO 对象
        ApplyFormBO applyFormBO = ApplyFormConverter.INSTANCE.dtoToBO(applyFormDTO);

        // 调用领域服务保存申请数据
        ApplyFormBO savedApplyForm = applyFormDomainService.submitApply(applyFormBO);

        // 将保存后的 BO 转换为 VO 并返回
        ApplyFormVO applyFormVO = ApplyFormConverter.INSTANCE.boToVO(savedApplyForm);
        return Result.success(applyFormVO);
    }

    @ApiOperationLog(description = "审核申请")
    @PostMapping("/reviewApply")
    public Result<Boolean> reviewApply(@RequestBody ReviewDTO reviewDTO) {
        // 校验传入的审核数据是否为空
        if (Objects.isNull(reviewDTO)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_IS_NULL);
        }

        // 校验申请ID是否为空
        if (Objects.isNull(reviewDTO.getId())) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_ID_NOT_NULL);
        }

        // 调用领域服务进行审核操作
        Boolean result = applyFormDomainService.reviewApply(reviewDTO.getId(), reviewDTO.getStatus(), reviewDTO.getRemark());
        return Result.success(result);
    }

    @ApiOperationLog(description = "删除申请")
    @PostMapping("/deleteApply")
    public Result<Boolean> deleteApply(@RequestBody ApplyFormDTO applyFormDTO) {
        // 校验传入的申请数据是否为空
        if (Objects.isNull(applyFormDTO)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_IS_NULL);
        }

        // 获取申请 ID 并校验是否为空
        Long id = applyFormDTO.getId();
        if (Objects.isNull(id)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_ID_NOT_NULL);
        }

        // 调用领域服务删除申请
        Boolean result = applyFormDomainService.deleteApply(id);
        return Result.success(result);
    }

    @ApiOperationLog(description = "根据ID获取申请详情")
    @GetMapping("/getApplyFormById")
    public Result<ApplyFormVO> getApplyFormById(@RequestParam Long id) {
        // 校验申请 ID 是否为空
        if (Objects.isNull(id)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_ID_NOT_NULL);
        }

        // 调用领域服务获取申请数据
        ApplyFormBO applyFormBO = applyFormDomainService.getApplyFormById(id);

        // 校验申请数据是否存在
        if (Objects.isNull(applyFormBO)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_NOT_EXIST);
        }

        // 将 BO 转换为 VO 并返回
        ApplyFormVO applyFormVO = ApplyFormConverter.INSTANCE.boToVO(applyFormBO);
        return Result.success(applyFormVO);
    }
}
