package com.safewind.infra.basic.dao;

import com.safewind.infra.basic.entity.WsActivity;
import com.safewind.infra.basic.entity.WsQueryActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 申请表(WsActivity)表数据库访问层
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface WsActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WsActivity queryById(Long id);

    /**
     * 统计总行数
     *
     * @param wsActivity 查询条件
     * @return 总行数
     */
    long count(WsActivity wsActivity);

    /**
     * 新增数据
     *
     * @param wsActivity 实例对象
     * @return 影响行数
     */
    int insert(WsActivity wsActivity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WsActivity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WsActivity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WsActivity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WsActivity> entities);

    /**
     * 修改数据
     *
     * @param wsActivity 实例对象
     * @return 影响行数
     */
    int update(WsActivity wsActivity);

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
     * @param wsQueryActivity 查询条件
     * @param offset          偏移量
     * @param limit           查询数量
     * @return 对象列表
     */
    List<WsActivity> queryAllByLimit(@Param("query") WsQueryActivity wsQueryActivity,
                                     @Param("offset") Long offset,
                                     @Param("limit") Long limit);

    /**
     * 获取最新活动列表
     *
     * @param limit 限制数量
     * @return 最新活动列表
     */
    List<WsActivity> getLatestActivities(@Param("limit") Integer limit);

    /**
     * 获取活动数量
     *
     * @param wsQueryActivity 查询参数
     * @return 活动数量
     */
    long countActivity(@Param("query") WsQueryActivity wsQueryActivity);

}

