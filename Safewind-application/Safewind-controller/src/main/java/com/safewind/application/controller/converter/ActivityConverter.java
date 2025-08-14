package com.safewind.application.controller.converter;

import com.safewind.application.controller.dto.ActivityDTO;
import com.safewind.application.controller.dto.ActivityQueryDTO;
import com.safewind.application.controller.vo.WsActivityVO;
import com.safewind.domain.bo.ActivityBO;
import com.safewind.domain.bo.ActivityListBO;
import com.safewind.domain.bo.ActivityQueryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:10
 * @description: TODO
 */
@Mapper
public interface ActivityConverter {

    ActivityConverter INSTANCE = Mappers.getMapper(ActivityConverter.class);

    /**
     * BO转VO
     */
    WsActivityVO boToVO(ActivityBO activityBO);

    /**
     * BO列表转VO列表
     */
    List<WsActivityVO> boListToVOList(List<ActivityBO> activityBOList);

    /**
     * 列表BO转VO
     */
    WsActivityVO listBOToVO(ActivityListBO activityListBO);

    /**
     * 列表BO列表转VO列表
     */
    List<WsActivityVO> listBOListToVOList(List<ActivityListBO> activityListBOList);

    /**
     * DTO转BO
     */
    ActivityBO dtoToBO(ActivityDTO activityDTO);

    /**
     * 查询DTO转查询BO
     */
    ActivityQueryBO queryDTOToQueryBO(ActivityQueryDTO queryDTO);
}
