package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:28
 * @description: 新闻查询DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsQueryDTO extends Page {
    /**
     * 新闻标题（模糊查询）
     */
    private String title;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
