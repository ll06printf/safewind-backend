package com.safewind.domain.service.impl;

import com.safewind.common.enums.ActivityExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.ActivityBO;
import com.safewind.domain.bo.ActivityListBO;
import com.safewind.domain.bo.ActivityQueryBO;
import com.safewind.domain.converter.ActivityDomainConverter;
import com.safewind.domain.markdown.MarkdownHelper;
import com.safewind.domain.service.ActivityDomainService;
import com.safewind.infra.basic.entity.WsActivity;
import com.safewind.infra.basic.entity.WsQueryActivity;
import com.safewind.infra.basic.service.WsActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:09
 * @description: TODO
 */
@Service
public class ActivityDomainServiceImpl implements ActivityDomainService {

    @Resource
    private WsActivityService wsActivityService;

    @Override
    public PageResult<ActivityBO> queryActivityPage(ActivityQueryBO queryBO) {
        // 构建查询条件
        WsQueryActivity query = new WsQueryActivity();
        query.setTitle(queryBO.getTitle());
        query.setStartTime(queryBO.getStartTime());
        query.setEndTime(queryBO.getEndTime());
        // 调用基础设施层服务
        PageResult<WsActivity> pageResult = wsActivityService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());

        // 转换为领域对象
        List<ActivityBO> activityBOList = ActivityDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        return PageResult.<ActivityBO>builder()
                .data(activityBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }

    @Override
    public ActivityBO getActivityById(Long id) {

        WsActivity wsActivity = wsActivityService.queryById(id);
        if (wsActivity == null) {
            return null;
        }
        ActivityBO activityBO = ActivityDomainConverter.INSTANCE.entityToBO(wsActivity);
        // 转化成 md 格式
        return activityBO;
    }

    @Override
    public ActivityBO addActivity(ActivityBO activityBO) {
        WsActivity wsActivity = ActivityDomainConverter.INSTANCE.boToEntity(activityBO);
        WsActivity savedActivity = wsActivityService.insert(wsActivity);
        return ActivityDomainConverter.INSTANCE.entityToBO(savedActivity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActivityBO updateActivity(ActivityBO activityBO) {
        WsActivity wsActivity = ActivityDomainConverter.INSTANCE.boToEntity(activityBO);
        // 检查是否存在
        WsActivity wsActivityToUpdate = wsActivityService.queryById(wsActivity.getId());
        if (wsActivityToUpdate == null) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_NOT_EXIST);
        }
        WsActivity updatedActivity = wsActivityService.update(wsActivity);
        return ActivityDomainConverter.INSTANCE.entityToBO(updatedActivity);
    }

    @Override
    public Boolean deleteActivity(Long id) {
        // 检查是否存在
        WsActivity wsActivity = wsActivityService.queryById(id);
        if (wsActivity == null) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_NOT_EXIST);
        }
        return wsActivityService.deleteById(id);
    }

    @Override
    public List<ActivityListBO> getLatestActivities(Integer limit) {
        List<WsActivity> latestActivities = wsActivityService.getLatestActivities(limit);
         // 转化成 MD
        latestActivities.forEach(activity -> {
            activity.setIntroduction(MarkdownHelper.convertMarkdown2Html(activity.getIntroduction()));
        });
        return ActivityDomainConverter.INSTANCE.entityListToListBOList(latestActivities);
    }

    @Override
    public ActivityBO getWsActivityById(Long id) {
        WsActivity wsActivity = wsActivityService.queryById(id);
        if (wsActivity == null) {
            return null;
        }
        // 转化 MD
        wsActivity.setIntroduction(MarkdownHelper.convertMarkdown2Html(wsActivity.getIntroduction()));
        return ActivityDomainConverter.INSTANCE.entityToBO(wsActivity);
    }

    @Override
    public PageResult<ActivityBO> queryWsActivityPage(ActivityQueryBO queryBO) {
        // 构建查询条件
        WsQueryActivity query = new WsQueryActivity();
        query.setTitle(queryBO.getTitle());
        query.setStartTime(queryBO.getStartTime());
        query.setEndTime(queryBO.getEndTime());
        // 调用基础设施层服务
        PageResult<WsActivity> pageResult = wsActivityService.queryPage(query, queryBO.getPageNum(), queryBO.getPageSize());

        // 转换为领域对象
        List<ActivityBO> activityBOList = ActivityDomainConverter.INSTANCE.entityListToBOList(pageResult.getData());

        // 转化 MD
        activityBOList.forEach(activity -> {
            activity.setIntroduction(MarkdownHelper.convertMarkdown2Html(activity.getIntroduction()));
        });

        return PageResult.<ActivityBO>builder()
                .data(activityBOList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
    }
}
