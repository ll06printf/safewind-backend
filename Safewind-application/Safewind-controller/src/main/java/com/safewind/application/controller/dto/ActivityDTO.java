package com.safewind.application.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:09
 * @description: TODO
 */
@Data
public class ActivityDTO {
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
     */
    private String isLink;

    /**
     * 外链地址
     */
    private String externalLink;
}
