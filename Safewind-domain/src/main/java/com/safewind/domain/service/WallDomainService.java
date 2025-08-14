package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.WallBO;
import com.safewind.domain.bo.WallListBO;
import com.safewind.domain.bo.WallQueryBO;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:13
 * @description: 弹幕墙领域服务
 */
public interface WallDomainService {

    /**
     * 分页查询弹幕列表
     */
    PageResult<WallBO> queryWallPage(WallQueryBO queryBO);

    /**
     * 添加弹幕
     */
    WallBO addWall(WallBO wallBO);

    /**
     * 修改弹幕
     */
    WallBO updateWall(WallBO wallBO);

    /**
     * 删除弹幕
     */
    Boolean deleteWall(Long id);

    /**
     * 根据ID获取弹幕详情
     */
    WallBO getWallById(Long id);

    /**
     * 获取最新弹幕
     */
    List<WallListBO> getLatestWalls(Integer limit);
}
