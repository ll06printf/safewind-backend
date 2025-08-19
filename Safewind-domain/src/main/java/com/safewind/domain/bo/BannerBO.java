package com.safewind.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-19  00:00:00
 * @description: 横幅领域对象
 */
@Data
@Builder
public class BannerBO {
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
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}