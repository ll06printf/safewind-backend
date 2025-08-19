package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.ApplyFormDTO;
import com.safewind.application.controller.dto.ApplyFormQueryDTO;
import com.safewind.application.controller.vo.ApplyFormVO;
import com.safewind.domain.bo.ApplyFormBO;
import com.safewind.domain.bo.ApplyFormQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:20
 * @description: 申请表转换器
 *  * 使用MapStruct进行对象之间的转换，包括DTO、BO、VO之间的相互转换
 */
@Mapper
public interface ApplyFormConverter {
    /**
     * 转换器实例
     */
    ApplyFormConverter INSTANCE = Mappers.getMapper(ApplyFormConverter.class);

    /**
     * DTO转BO
     *
     * @param applyFormDTO 申请表DTO对象
     * @return 申请表BO对象
     */
    ApplyFormBO dtoToBO(ApplyFormDTO applyFormDTO);

    /**
     * BO转DTO
     *
     * @param applyFormBO 申请表BO对象
     * @return 申请表DTO对象
     */
    ApplyFormDTO boToDTO(ApplyFormBO applyFormBO);

    /**
     * BO转VO
     *
     * @param applyFormBO 申请表BO对象
     * @return 申请表VO对象
     */
    ApplyFormVO boToVO(ApplyFormBO applyFormBO);

    /**
     * BO列表转VO列表
     *
     * @param applyFormBOList 申请表BO列表
     * @return 申请表VO列表
     */
    List<ApplyFormVO> boListToVOList(List<ApplyFormBO> applyFormBOList);

    /**
     * 查询DTO转查询BO
     *
     * @param applyFormQueryDTO 申请表查询DTO对象
     * @return 申请表查询BO对象
     */
    ApplyFormQueryBO queryDTOToQueryBO(ApplyFormQueryDTO applyFormQueryDTO);
}
