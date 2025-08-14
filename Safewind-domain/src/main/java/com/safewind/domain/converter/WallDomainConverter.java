package com.safewind.domain.converter;

import com.safewind.domain.bo.WallBO;
import com.safewind.domain.bo.WallListBO;
import com.safewind.infra.basic.entity.WsWall;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:13
 * @description: 弹幕墙领域转换器
 */
@Mapper
public interface WallDomainConverter {
    WallDomainConverter INSTANCE = Mappers.getMapper(WallDomainConverter.class);

    WallBO entityToWallBO(WsWall wsWall);
    WsWall wallBOToEntity(WallBO wallBO);

    WallListBO entityToWallListBO(WsWall wsWall);

    List<WallBO> entityListToBOList(List<WsWall> wsWallList);
    List<WallListBO> entityListToListBOList(List<WsWall> wsWallList);
}
