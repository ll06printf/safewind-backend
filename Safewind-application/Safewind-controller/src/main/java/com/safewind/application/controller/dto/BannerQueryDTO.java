package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-01-01  00:00:00
 * @description: 横幅查询DTO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerQueryDTO extends Page {
    /**
     * 横幅标题（模糊查询）
     */
    private String title;

    /**
     * 副标题（模糊查询）
     */
    private String subTitle;
}