package com.safewind.application.controller.dto;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅DTO
 */
@Data
public class BannerDTO {
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
}