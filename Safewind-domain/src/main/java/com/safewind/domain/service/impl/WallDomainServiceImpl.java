package com.safewind.domain.service.impl;

import com.safewind.common.enums.WallExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.page.PageUtils;
import com.safewind.domain.bo.WallBO;
import com.safewind.domain.bo.WallListBO;
import com.safewind.domain.bo.WallQueryBO;
import com.safewind.domain.converter.WallDomainConverter;
import com.safewind.domain.service.WallDomainService;
import com.safewind.infra.basic.entity.WsWall;
import com.safewind.infra.basic.entity.WsQueryWall;
import com.safewind.infra.basic.service.WsWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:13
 * @description: 弹幕墙领域服务实现类
 */
@Service
public class WallDomainServiceImpl implements WallDomainService {

    @Autowired
    private WsWallService wsWallService;

    @Override
    public PageResult<WallBO> queryWallPage(WallQueryBO queryBO) {
        // 构建查询条件
        WsQueryWall query = new WsQueryWall();
        query.setName(queryBO.getName());
        query.setContent(queryBO.getContent());

        // 调用基础设施层服务
        PageResult<WsWall> pageResult = wsWallService.queryPage(query,
                queryBO.getPageNum(),
                queryBO.getPageSize());

        // 转换为领域对象
        List<WallBO> wallBOList = WallDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        return PageResult.<WallBO>builder()
                .data(wallBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public WallBO addWall(WallBO wallBO) {
        if (Objects.isNull(wallBO)) {
            throw new BizException(WallExceptionEnum.WALL_IS_NULL);
        }

        WsWall wsWall = WallDomainConverter.INSTANCE.wallBOToEntity(wallBO);
        WsWall savedWall = wsWallService.insert(wsWall);
        return WallDomainConverter.INSTANCE.entityToWallBO(savedWall);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WallBO updateWall(WallBO wallBO) {
        if (Objects.isNull(wallBO)) {
            throw new BizException(WallExceptionEnum.WALL_IS_NULL);
        }
        if (Objects.isNull(wallBO.getId())) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }

        WsWall wsWall = WallDomainConverter.INSTANCE.wallBOToEntity(wallBO);
        // 检查是否存在
        WsWall wsWallToUpdate = wsWallService.queryById(wsWall.getId());
        if (wsWallToUpdate == null) {
            throw new BizException(WallExceptionEnum.WALL_NOT_EXIST);
        }
        WsWall updatedWall = wsWallService.update(wsWall);
        return WallDomainConverter.INSTANCE.entityToWallBO(updatedWall);
    }

    @Override
    public Boolean deleteWall(Long id) {
        if (Objects.isNull(id)) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }

        // 检查是否存在
        WsWall wsWall = wsWallService.queryById(id);
        if (wsWall == null) {
            throw new BizException(WallExceptionEnum.WALL_NOT_EXIST);
        }

        return wsWallService.deleteById(id);
    }

    @Override
    public WallBO getWallById(Long id) {
        if (Objects.isNull(id)) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }

        WsWall wsWall = wsWallService.queryById(id);
        if (Objects.isNull(wsWall)) {
            throw new BizException(WallExceptionEnum.WALL_NOT_EXIST);
        }

        return WallDomainConverter.INSTANCE.entityToWallBO(wsWall);
    }

    @Override
    public List<WallListBO> getLatestWalls(Integer limit) {
        List<WsWall> walls = wsWallService.getLatestWalls(limit);
        return WallDomainConverter.INSTANCE.entityListToListBOList(walls);
    }
}
