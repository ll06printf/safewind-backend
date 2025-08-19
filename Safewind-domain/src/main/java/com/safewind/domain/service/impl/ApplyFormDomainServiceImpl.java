package com.safewind.domain.service.impl;

import com.safewind.common.enums.ApplyFormExceptionEnum;
import com.safewind.common.enums.ReviewStatusEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.ApplyFormBO;
import com.safewind.domain.bo.ApplyFormQueryBO;
import com.safewind.domain.bo.ReviewBO;
import com.safewind.domain.converter.ApplyFormDomainConverter;
import com.safewind.domain.service.ApplyFormDomainService;
import com.safewind.infra.basic.entity.SysApplyForm;
import com.safewind.infra.basic.entity.SysReview;
import com.safewind.infra.basic.service.SysApplyFormService;
import com.safewind.infra.basic.service.SysReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-15  12:00
 * @description: 申请业务
 */
@Slf4j
@Service
public class ApplyFormDomainServiceImpl implements ApplyFormDomainService {

    @Autowired
    private SysApplyFormService sysApplyFormService;

    @Autowired
    private SysReviewService sysReviewService;

    @Override
    public PageResult<ApplyFormBO> queryApplyFormPage(ApplyFormQueryBO queryBO) {
        // 构建查询条件
        SysApplyForm query = new SysApplyForm();
        query.setName(queryBO.getName());
        query.setStudentId(queryBO.getStudentId());
        query.setDeptId(queryBO.getDeptId());

        // 调用基础设施层服务
        PageResult<SysApplyForm> pageResult = sysApplyFormService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());


        // 转换为领域对象
        List<ApplyFormBO> applyFormBOList = ApplyFormDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        // 获取审核表 todo:可以优化，数据库批量查询
        applyFormBOList.forEach(applyFormBO -> {
            SysReview sysReview = sysReviewService.queryByApplyId(applyFormBO.getId(),ReviewStatusEnum.WAIT_REVIEW.getCode());
            if (sysReview != null) {
                applyFormBO.setReview(ApplyFormDomainConverter.INSTANCE.entityToReviewBO(sysReview));
            }
        });

        return PageResult.<ApplyFormBO>builder()
                .data(applyFormBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public ApplyFormBO getApplyFormById(Long id) {
        // 校验参数
        if (Objects.isNull(id)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_ID_NOT_NULL);
        }

        SysApplyForm sysApplyForm = sysApplyFormService.queryById(id);
        if (sysApplyForm == null) {
            return null;
        }

        ApplyFormBO applyFormBO = ApplyFormDomainConverter.INSTANCE.entityToBO(sysApplyForm);
        SysReview sysReview = sysReviewService.queryByApplyId(applyFormBO.getId(),null);
        applyFormBO.setReview(ApplyFormDomainConverter.INSTANCE.entityToReviewBO(sysReview));
        return applyFormBO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApplyFormBO submitApply(ApplyFormBO applyFormBO) {
        // 校验参数
        if (Objects.isNull(applyFormBO)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_IS_NULL);
        }
        // 转换参数
        SysApplyForm sysApplyForm = ApplyFormDomainConverter.INSTANCE.boToEntity(applyFormBO);

        // 检查学号是否存在
        SysApplyForm studentId = sysApplyFormService.queryByStudentId(sysApplyForm.getStudentId());
        if (studentId != null) {
            throw new BizException(ApplyFormExceptionEnum.STUDENT_ID_EXIST);
        }

        // 1.保存申请
        SysApplyForm savedApplyForm = sysApplyFormService.insert(sysApplyForm);
        log.info("保存申请成功：{}", savedApplyForm);

        // 2.生成审核表
        SysReview sysReview = new SysReview();
        sysReview.setApplyId(savedApplyForm.getId());
        sysReview.setState(ReviewStatusEnum.WAIT_REVIEW.getCode());

        // 3.保存审核表
        SysReview saveReview = sysReviewService.insert(sysReview);
        log.info("保存审核表成功：{}", saveReview);

        ApplyFormBO saveApplyFromBO = ApplyFormDomainConverter.INSTANCE.entityToBO(savedApplyForm);
        saveApplyFromBO.setReview(ApplyFormDomainConverter.INSTANCE.entityToReviewBO(saveReview));
        return saveApplyFromBO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean reviewApply(Long id, Integer status, String remark) {
        // 校验参数
        if (Objects.isNull(id)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_ID_NOT_NULL);
        }
        if (Objects.isNull(status)) {
            throw new BizException(ApplyFormExceptionEnum.REVIEW_STATUS_NOT_NULL);
        }

        // 检查申请表是否存在
        SysApplyForm sysApplyForm = sysApplyFormService.queryById(id);
        if (sysApplyForm == null) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_NOT_EXIST);
        }

        // 检查审核表是否存在
        SysReview sysReview = sysReviewService.queryByApplyId(id,ReviewStatusEnum.WAIT_REVIEW.getCode());
        if (sysReview == null) {
            throw new BizException(ApplyFormExceptionEnum.REVIEW_NOT_EXIST);
        }

        // 更新审核状态
        sysReview.setState(status);
        sysReview.setRemark(remark);
        SysReview updateReview = sysReviewService.update(sysReview);

        return !Objects.isNull(updateReview);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteApply(Long id) {
        // 校验参数
        if (Objects.isNull(id)) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_ID_NOT_NULL);
        }

        // 检查审核表是否存在
        SysReview sysReview = sysReviewService.queryByApplyId(id,ReviewStatusEnum.WAIT_REVIEW.getCode());
        if (sysReview == null) {
            throw new BizException(ApplyFormExceptionEnum.REVIEW_NOT_EXIST);
        }
        // 1.删除审核表
        sysReviewService.deleteById(sysReview.getId());

        // 检查申请是否存在
        SysApplyForm sysApplyForm = sysApplyFormService.queryById(id);
        if (sysApplyForm == null) {
            throw new BizException(ApplyFormExceptionEnum.APPLY_FORM_NOT_EXIST);
        }
        // 2.删除申请表
        boolean b = sysApplyFormService.deleteById(id);

        return b;
    }
}
