package com.safewind.domain.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:20
 * @description: 新闻列表领域对象
 */
@Data
@Builder
public class NewsListBO {
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
