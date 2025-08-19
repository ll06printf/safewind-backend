package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.DepartmentBO;
import com.safewind.domain.bo.DepartmentQueryBO;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-15  01:21
 * @description: TODO
 */
public interface DepartmentDomainService {

    /**
     * 分页查询部门列表
     * 根据查询条件分页获取部门信息
     *
     * @param queryBO 查询条件业务对象
     * @return 分页结果，包含部门列表和分页信息
     */
    PageResult<DepartmentBO> queryDepartmentPage(DepartmentQueryBO queryBO);

    /**
     * 根据ID获取部门详情
     * 根据部门ID获取单个部门的详细信息
     *
     * @param id 部门ID
     * @return 部门业务对象，如果不存在则返回null
     */
    DepartmentBO getDepartmentById(Long id);

    /**
     * 新增部门
     * 创建新的部门记录
     *
     * @param departmentBO 部门业务对象
     * @return 保存后的部门业务对象，包含生成的ID等信息
     */
    DepartmentBO addDepartment(DepartmentBO departmentBO);

    /**
     * 更新部门
     * 根据ID更新部门信息
     *
     * @param departmentBO 部门业务对象，必须包含ID
     * @return 更新后的部门业务对象
     */
    DepartmentBO updateDepartment(DepartmentBO departmentBO);

    /**
     * 删除部门
     * 根据ID逻辑删除部门记录
     *
     * @param id 部门ID
     * @return 删除操作是否成功
     */
    Boolean deleteDepartment(Long id);

    /**
     * 获取所有部门
     * 获取所有未删除的部门列表，用于下拉选择等场景
     *
     * @return 部门业务对象列表
     */
    List<DepartmentBO> getAllDepartments();
}
