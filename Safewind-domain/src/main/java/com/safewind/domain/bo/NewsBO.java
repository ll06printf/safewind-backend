package com.safewind.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:16
 * @description: 新闻领域对象
 */
@Data
@Builder
public class NewsBO {
    /**
     * 新闻ID
     */
    private Long id;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 新闻内容/html
     */
    private String htmlContent;

    /**
     * 封面图片
     */
    private String coverImg;

    /**
     * 是否外链
     * */
    private String isLink;

    /**
     * 外链
     * */
    private String externalLink;

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
