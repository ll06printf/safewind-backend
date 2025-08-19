package com.safewind.domain.converter;

import com.safewind.domain.bo.ApplyFormBO;
import com.safewind.domain.bo.ApplyFormQueryBO;
import com.safewind.domain.bo.ReviewBO;
import com.safewind.infra.basic.entity.SysApplyForm;
import com.safewind.infra.basic.entity.SysReview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:39
 * @description: TODO
 */
@Mapper
public interface ApplyFormDomainConverter {
    /**
     * 转换器实例
     */
    ApplyFormDomainConverter INSTANCE = Mappers.getMapper(ApplyFormDomainConverter.class);

    /**
     * Entity转BO
     *
     * @param sysApplyForm 申请表实体对象
     * @return 申请表BO对象
     */
    ApplyFormBO entityToBO(SysApplyForm sysApplyForm);

    /**
     * BO转Entity
     *
     * @param applyFormBO 申请表BO对象
     * @return 申请表实体对象
     */
    SysApplyForm boToEntity(ApplyFormBO applyFormBO);

    /**
     * Entity列表转BO列表
     *
     * @param sysApplyFormList 申请表实体列表
     * @return 申请表BO列表
     */
    List<ApplyFormBO> entityListToBOList(List<SysApplyForm> sysApplyFormList);

    /**
     * 查询BO转Entity查询条件
     *
     * @param queryBO 查询BO对象
     * @return 申请表实体对象（用于查询条件）
     */
    SysApplyForm queryBOToEntity(ApplyFormQueryBO queryBO);

    /**
     * Entity转ReviewBO
     *
     * @param sysReview 审核实体对象
     * @return 审核BO对象
     */
    ReviewBO entityToReviewBO(SysReview sysReview);
}
