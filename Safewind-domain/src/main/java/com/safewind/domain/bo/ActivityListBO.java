package com.safewind.domain.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:07
 * @description: TODO
 */
@Data
public class ActivityListBO {
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
}
