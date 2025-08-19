package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.DepartmentDTO;
import com.safewind.application.controller.dto.DepartmentQueryDTO;
import com.safewind.application.controller.vo.DepartmentVO;
import com.safewind.domain.bo.DepartmentBO;
import com.safewind.domain.bo.DepartmentQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-15  01:19
 * @Description: 部门转换器
 *  * 使用MapStruct进行对象之间的转换，包括DTO、BO、VO之间的相互转换
 */
@Mapper
public interface DepartmentConverter {
    /**
     * 转换器实例
     */
    DepartmentConverter INSTANCE = Mappers.getMapper(DepartmentConverter.class);

    /**
     * DTO转BO
     *
     * @param departmentDTO 部门DTO对象
     * @return 部门BO对象
     */
    DepartmentBO dtoToBO(DepartmentDTO departmentDTO);

    /**
     * BO转DTO
     *
     * @param departmentBO 部门BO对象
     * @return 部门DTO对象
     */
    DepartmentDTO boToDTO(DepartmentBO departmentBO);

    /**
     * BO转VO
     *
     * @param departmentBO 部门BO对象
     * @return 部门VO对象
     */
    DepartmentVO boToVO(DepartmentBO departmentBO);

    /**
     * BO列表转VO列表
     *
     * @param departmentBOList 部门BO列表
     * @return 部门VO列表
     */
    List<DepartmentVO> boListToVOList(List<DepartmentBO> departmentBOList);

    /**
     * 查询DTO转查询BO
     *
     * @param departmentQueryDTO 部门查询DTO对象
     * @return 部门查询BO对象
     */
    DepartmentQueryBO queryDTOToQueryBO(DepartmentQueryDTO departmentQueryDTO);
}
