package com.safewind.domain.service;

import com.safewind.common.page.PageResult;
import com.safewind.domain.bo.ActivityBO;
import com.safewind.domain.bo.ActivityListBO;
import com.safewind.domain.bo.ActivityQueryBO;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:08
 * @description: TODO
 */
public interface ActivityDomainService {
    /**
     * 分页查询活动列表
     */
    PageResult<ActivityBO> queryActivityPage(ActivityQueryBO queryBO);

    /**
     * 根据ID查询活动详情
     */
    ActivityBO getActivityById(Long id);

    /**
     * 新增活动
     */
    ActivityBO addActivity(ActivityBO activityBO);

    /**
     * 更新活动
     */
    ActivityBO updateActivity(ActivityBO activityBO);

    /**
     * 删除活动
     */
    Boolean deleteActivity(Long id);

    /**
     * 获取最新活动列表
     */
    List<ActivityListBO> getLatestActivities(Integer limit);
}
