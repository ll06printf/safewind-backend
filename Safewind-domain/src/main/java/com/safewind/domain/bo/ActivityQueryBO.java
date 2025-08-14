package com.safewind.domain.bo;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:06
 * @description: TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityQueryBO extends Page {
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
