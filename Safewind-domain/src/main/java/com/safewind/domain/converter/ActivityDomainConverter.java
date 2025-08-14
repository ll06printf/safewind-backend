package com.safewind.domain.converter;

import com.safewind.domain.bo.ActivityBO;
import com.safewind.domain.bo.ActivityListBO;
import com.safewind.infra.basic.entity.WsActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:07
 * @description: TODO
 */
@Mapper
public interface ActivityDomainConverter {
    ActivityDomainConverter INSTANCE = Mappers.getMapper(ActivityDomainConverter.class);

    /**
     * 实体转BO
     */
    ActivityBO entityToBO(WsActivity wsActivity);

    /**
     * BO转实体
     */
    WsActivity boToEntity(ActivityBO activityBO);

    /**
     * 实体列表转BO列表
     */
    List<ActivityBO> entityListToBOList(List<WsActivity> wsActivityList);

    /**
     * 实体转列表BO
     */
    ActivityListBO entityToListBO(WsActivity wsActivity);

    /**
     * 实体列表转列表BO列表
     */
    List<ActivityListBO> entityListToListBOList(List<WsActivity> wsActivityList);
}
