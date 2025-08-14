package com.safewind.application.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-11  23:28
 * @description: 新闻DTO
 */
@Data
public class NewsDTO {
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
}
