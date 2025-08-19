package com.safewind.infra.basic.service;

import com.safewind.common.page.PageResult;
import com.safewind.infra.basic.entity.SysApplyForm;

/**
 * 申请表(SysApplyForm)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:27
 */
public interface SysApplyFormService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysApplyForm queryById(Long id);

    /**
     * 新增数据
     *
     * @param sysApplyForm 实例对象
     * @return 实例对象
     */
    SysApplyForm insert(SysApplyForm sysApplyForm);

    /**
     * 修改数据
     *
     * @param sysApplyForm 实例对象
     * @return 实例对象
     */
    SysApplyForm update(SysApplyForm sysApplyForm);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 逻辑删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean softDeleteById(Long id);

    /**
     * 分页查询申请列表
     *
     * @param sysApplyForm 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    PageResult<SysApplyForm> queryPage(SysApplyForm sysApplyForm, Long pageNum, Long pageSize);

    /**
     * 根据学号查询申请
     *
     * @param studentId 学号
     * @return 申请
     */
    SysApplyForm queryByStudentId(String studentId);
}
