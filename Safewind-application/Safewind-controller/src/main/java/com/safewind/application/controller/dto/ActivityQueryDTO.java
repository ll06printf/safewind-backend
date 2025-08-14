package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:10
 * @description: TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityQueryDTO extends Page {
    /**
     * 活动标题（模糊查询）
     */
    private String title;

    /**
     * 活动简介（模糊查询）
     */
    private String brief;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
