package com.safewind.infra.basic.dao;

import com.safewind.common.page.PageResult;
import com.safewind.infra.basic.entity.WsWall;
import com.safewind.infra.basic.entity.WsQueryWall;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 海风墙(WsWall)表数据库访问层
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface WsWallDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WsWall queryById(Long id);

    /**
     * 限制查询数量的弹幕
     *
     * @param limit 限制数量
     * @return 实例对象列表
     */
    List<WsWall> queryByLimit(@Param("limit") Integer limit);

    /**
     * 分页查询弹幕列表
     *
     * @param queryWall 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    List<WsWall> queryPage(@Param("queryWall") WsQueryWall queryWall,
                                 @Param("pageNum") Long pageNum,
                                 @Param("pageSize") Long pageSize);

    /**
     * 统计总行数
     *
     * @param queryWall 查询条件
     * @return 总行数
     */
    long count(@Param("queryWall") WsQueryWall queryWall);

    /**
     * 新增数据
     *
     * @param wsWall 实例对象
     * @return 影响行数
     */
    int insert(WsWall wsWall);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WsWall> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WsWall> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WsWall> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WsWall> entities);

    /**
     * 修改数据
     *
     * @param wsWall 实例对象
     * @return 影响行数
     */
    int update(WsWall wsWall);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 统计分页总行数
     *
     * @param queryWall 查询条件
     * @return 总行数
     */
    long countPage(@Param("queryWall") WsQueryWall queryWall);
}
