package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.ApplyFormBO;
import com.safewind.domain.bo.ApplyFormQueryBO;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:23
 * @description: 申请表领域服务接口
 *  * 定义申请表相关的业务操作，包括提交、审核、查询等功能
 */
public interface ApplyFormDomainService {

    /**
     * 分页查询申请列表
     * 根据查询条件分页获取申请记录
     *
     * @param queryBO 查询条件业务对象
     * @return 分页结果，包含申请列表和分页信息
     */
    PageResult<ApplyFormBO> queryApplyFormPage(ApplyFormQueryBO queryBO);

    /**
     * 根据ID获取申请详情
     * 根据申请ID获取单个申请的详细信息
     *
     * @param id 申请ID
     * @return 申请业务对象，如果不存在则返回null
     */
    ApplyFormBO getApplyFormById(Long id);

    /**
     * 提交申请
     * 创建新的申请记录
     *
     * @param applyFormBO 申请业务对象
     * @return 保存后的申请业务对象，包含生成的ID等信息
     */
    ApplyFormBO submitApply(ApplyFormBO applyFormBO);

    /**
     * 审核申请
     * 根据申请ID更新申请状态
     *
     * @param id 申请ID
     * @param status 审核状态：1-通过，2-拒绝
     * @param remark 审核备注
     * @return 审核操作是否成功
     */
    Boolean reviewApply(Long id, Integer status, String remark);

    /**
     * 删除申请
     * 根据ID逻辑删除申请记录
     *
     * @param id 申请ID
     * @return 删除操作是否成功
     */
    Boolean deleteApply(Long id);
}
