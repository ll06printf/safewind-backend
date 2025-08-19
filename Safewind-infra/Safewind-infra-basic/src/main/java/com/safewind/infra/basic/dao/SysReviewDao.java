package com.safewind.infra.basic.dao;

import com.safewind.infra.basic.entity.SysReview;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SysReview)表数据库访问层
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface SysReviewDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysReview queryById(Long id);


    /**
     * 统计总行数
     *
     * @param sysReview 查询条件
     * @return 总行数
     */
    long count(SysReview sysReview);

    /**
     * 新增数据
     *
     * @param sysReview 实例对象
     * @return 影响行数
     */
    int insert(SysReview sysReview);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysReview> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysReview> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysReview> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysReview> entities);

    /**
     * 修改数据
     *
     * @param sysReview 实例对象
     * @return 影响行数
     */
    int update(SysReview sysReview);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过主键软删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int softDeleteById(Long id);

    /**
     * 通过申请ID查询单条数据
     *
     * @param applyId 申请ID
     * @return 影响行数
     */
    SysReview queryByApplyId(@Param("applyId") Long applyId,@Param("status") Integer status);
}

