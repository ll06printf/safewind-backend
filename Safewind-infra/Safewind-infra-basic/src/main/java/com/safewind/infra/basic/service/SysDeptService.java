package com.safewind.infra.basic.service;

import com.safewind.common.page.PageResult;
import com.safewind.infra.basic.entity.SysDept;

import java.util.List;

/**
 * 部门表(SysDept)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
public interface SysDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDept queryById(Long id);

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    SysDept insert(SysDept sysDept);

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    SysDept update(SysDept sysDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页查询部门列表
     *
     * @param sysDept 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    PageResult<SysDept> queryPage(SysDept sysDept, Long pageNum, Long pageSize);

    /**
     * 获取所有部门
     *
     * @return 部门列表
     */
    List<SysDept> getAllDepartments();
}
