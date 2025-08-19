package com.safewind.infra.basic.dao;

import com.safewind.infra.basic.entity.WsBanner;
import com.safewind.infra.basic.entity.WsBannerQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface WsBannerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WsBanner queryById(Long id);

    /**
     * 统计总行数
     *
     * @param wsBanner 查询条件
     * @return 总行数
     */
    long count(WsBanner wsBanner);

    /**
     * 新增数据
     *
     * @param wsBanner 实例对象
     * @return 影响行数
     */
    int insert(WsBanner wsBanner);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WsBanner> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WsBanner> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WsBanner> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WsBanner> entities);

    /**
     * 修改数据
     *
     * @param wsBanner 实例对象
     * @return 影响行数
     */
    int update(WsBanner wsBanner);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @param offset 偏移量
     * @param limit 页面大小
     * @return 结果
     */
    List<WsBanner> queryPage(@Param("query") WsBannerQuery query, @Param("offset") long offset, @Param("limit") long limit);
}