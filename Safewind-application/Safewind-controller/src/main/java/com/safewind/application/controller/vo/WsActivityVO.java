package com.safewind.application.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:41
 * @description: 官网活动展示实体
 */
@Data
public class WsActivityVO {
    /**
     * 活动ID
     */
    private Long id;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动简介
     */
    private String brief;

    /**
     * 活动详情
     */
    private String introduction;

    /**
     * 活动时间
     */
    private LocalDateTime startTime;

    /**
     * 活动图片
     */
    private String picture;

    /**
     * 是否外链
     * */
    private String isLink;

    /**
     * 外链
     * */
    private String externalLink;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
