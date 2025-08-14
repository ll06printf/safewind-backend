package com.safewind.infra.basic.service;

import com.safewind.common.page.PageResult;
import com.safewind.infra.basic.entity.WsWall;
import com.safewind.infra.basic.entity.WsQueryWall;

import java.util.List;

/**
 * 海风墙(WsWall)表服务接口
 *
 * @author Darven
 * @since 2025-05-21 21:46:55
 */
public interface WsWallService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WsWall queryById(Long id);

    /**
     * 新增数据
     *
     * @param wsWall 实例对象
     * @return 实例对象
     */
    WsWall insert(WsWall wsWall);

    /**
     * 修改数据
     *
     * @param wsWall 实例对象
     * @return 实例对象
     */
    WsWall update(WsWall wsWall);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 分页查询弹幕列表
     *
     * @param queryWall 查询条件
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    PageResult<WsWall> queryPage(WsQueryWall queryWall, Long pageNum, Long pageSize);

    /**
     * 获取最新弹幕列表
     *
     * @param limit 限制数量
     * @return 最新弹幕列表
     */
    List<WsWall> getLatestWalls(Integer limit);

    /**
     * 获取弹幕数量
     *
     * @param query 查询条件
     * @return 弹幕数量
     */
    long count(WsQueryWall query);
}
