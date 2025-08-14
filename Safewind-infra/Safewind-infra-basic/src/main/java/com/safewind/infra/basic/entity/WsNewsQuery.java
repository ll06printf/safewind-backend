package com.safewind.infra.basic.entity;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-12  03:47
 * @description: 新闻查询条件
 */
@Data
public class WsNewsQuery {
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
