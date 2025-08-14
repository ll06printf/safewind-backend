package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.WallDTO;
import com.safewind.application.controller.dto.WallQueryDTO;
import com.safewind.application.controller.vo.WsWallVO;
import com.safewind.domain.bo.WallBO;
import com.safewind.domain.bo.WallListBO;
import com.safewind.domain.bo.WallQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:09
 * @description: 弹幕墙转换器
 */
@Mapper
public interface WallConverter {
    WallConverter INSTANCE = Mappers.getMapper(WallConverter.class);

    WallBO dtoToBO(WallDTO wallDTO);
    WallQueryBO queryDTOToQueryBO(WallQueryDTO wallQueryDTO);

    WsWallVO boToVO(WallBO wallBO);
    WsWallVO listBOToVO(WallListBO wallListBO);

    List<WsWallVO> boListToVOList(List<WallBO> wallBOList);
    List<WsWallVO> listBOListToVOList(List<WallListBO> wallListBOList);
}
