package com.safewind.domain.converter;

import com.safewind.domain.bo.DepartmentBO;
import com.safewind.domain.bo.DepartmentQueryBO;
import com.safewind.infra.basic.entity.SysDept;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: Darven
 * @CreateTime: 2025-08-15  01:36
 * @Description: TODO
 */
@Mapper
public interface DepartmentDomainConverter {
    /**
     * 转换器实例
     */
    DepartmentDomainConverter INSTANCE = Mappers.getMapper(DepartmentDomainConverter.class);

    /**
     * Entity转BO
     *
     * @param sysDept 部门实体对象
     * @return 部门BO对象
     */
    DepartmentBO entityToBO(SysDept sysDept);

    /**
     * BO转Entity
     *
     * @param departmentBO 部门BO对象
     * @return 部门实体对象
     */
    SysDept boToEntity(DepartmentBO departmentBO);

    /**
     * Entity列表转BO列表
     *
     * @param sysDeptList 部门实体列表
     * @return 部门BO列表
     */
    List<DepartmentBO> entityListToBOList(List<SysDept> sysDeptList);

    /**
     * 查询BO转Entity查询条件
     *
     * @param queryBO 查询BO对象
     * @return 部门实体对象（用于查询条件）
     */
    SysDept queryBOToEntity(DepartmentQueryBO queryBO);
}
