package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.converter.ApplyFormConverter;
import com.safewind.application.controller.dto.ApplyFormDTO;
import com.safewind.application.controller.vo.ApplyFormVO;
import com.safewind.common.annotation.Anonymous;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.ApplyFormExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.ApplyFormBO;
import com.safewind.domain.service.ApplyFormDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:35
 * @description: TODO
 */
@RestController
@RequestMapping("/ws-apply")
public class ApplyFormController {

    /**
     * 申请表领域服务，处理核心业务逻辑
     */
    @Autowired
    private ApplyFormDomainService applyFormDomainService;

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
}
