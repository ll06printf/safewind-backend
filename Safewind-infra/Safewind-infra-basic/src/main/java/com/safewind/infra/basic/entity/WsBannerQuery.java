package com.safewind.infra.basic.entity;

import lombok.Data;

/**
 * 横幅查询实体
 *
 * @author Darven
 * @since 2025-01-01 00:00:00
 */
@Data
public class WsBannerQuery {
    /**
     * 横幅标题（模糊查询）
     */
    private String title;
    /**
     * 副标题（模糊查询）
     */
    private String subTitle;
} 