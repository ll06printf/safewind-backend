package com.safewind.domain.bo;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅查询BO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BannerQueryBO extends Page {
    /**
     * 横幅标题（模糊查询）
     */
    private String title;

    /**
     * 副标题（模糊查询）
     */
    private String subTitle;
}