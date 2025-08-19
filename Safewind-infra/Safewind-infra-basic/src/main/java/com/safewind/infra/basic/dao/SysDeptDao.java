package com.safewind.infra.basic.dao;

import com.safewind.infra.basic.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(SysDept)表数据库访问层
 *
 * @author Darven
 * @since 2025-05-21 21:46:54
 */
public interface SysDeptDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDept queryById(Long id);

    /**
     * 统计总行数
     *
     * @param sysDept 查询条件
     * @return 总行数
     */
    long count(SysDept sysDept);

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 影响行数
     */
    int insert(SysDept sysDept);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDept> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysDept> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDept> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysDept> entities);

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 影响行数
     */
    int update(SysDept sysDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysDept 查询条件
     * @param offset 偏移量
     * @param limit 查询数量
     * @return 对象列表
     */
    List<SysDept> queryAllByLimit(@Param("sysDept") SysDept sysDept, @Param("offset") Long offset, @Param("limit") Long limit);

    /**
     * 查询所有部门
     *
     * @return 部门列表
     */
    List<SysDept> queryAll();
}
