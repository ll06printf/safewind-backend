package com.safewind.application.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅VO
 */
@Data
public class BannerVO {
    /**
     * 横幅ID
     */
    private Long id;

    /**
     * 横幅标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 背景图
     */
    private String backgroundPicture;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}